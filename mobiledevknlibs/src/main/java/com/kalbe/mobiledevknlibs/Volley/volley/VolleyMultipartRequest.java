package com.kalbe.mobiledevknlibs.Volley.volley;

/**
 * Created by arick.anjasmara on 21/07/2017.
 */

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Custom request to make multipart header and upload file.
 * <p>
 * Sketch Project Studio
 * Created by Angga on 27/04/2016 12.05.
 */
public class VolleyMultipartRequest extends Request<String> {
    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "apiclient-" + System.currentTimeMillis();

    private Response.Listener<String> mListener;
    private Response.ErrorListener mErrorListener;
    private Map<String, String> mHeaders;

    /**
     * Constructor with option method and default header configuration.
     *
     *  method        method for now accept POST and GET only
     *  url           request destination
     *  listener      on success event handler
     *  errorListener on error event handler
     */
    public VolleyMultipartRequest(int method, String url,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return (mHeaders != null) ? mHeaders : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + boundary;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            // populate text payload
            Map<String, String> params = getParams();
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }

            Map<String, DataPart> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }
            // populate data byte payload


            // close multipart form data after text and file data
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Custom method handle data payload.
     *
     *  Map data part label with data byte
     *  AuthFailureError
     */
    protected Map<String, DataPart> getByteData() throws AuthFailureError {
        return null;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            /*return Response.success(
                    response,
                    HttpHeaderParser.parseCacheHeaders(response));*/
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }

    /**
     * Parse string map into data output stream by key and value.
     *
     *  dataOutputStream data output stream handle string parsing
     *  params           string inputs collection
     *  encoding         encode the inputs, default UTF-8
     *  IOException
     */
    private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dataOutputStream, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encoding, uee);
        }
    }

    /**
     * Parse data into data output stream.
     *
     *  dataOutputStream data output stream handle file attachment
     *  data             loop through data
     *  IOException
     */
    private void dataParse(DataOutputStream dataOutputStream, Map<String, DataPart> data) throws IOException {
        for (Map.Entry<String, DataPart> entry : data.entrySet()) {
            buildDataPart(dataOutputStream, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Write string data into header and data output stream.
     *
     *  dataOutputStream data output stream handle string parsing
     *  parameterName    name of input
     *  parameterValue   value of input
     *  IOException
     */
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        //dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);
    }

    /**
     * Write data file into header and data output stream.
     *
     *  dataOutputStream data output stream handle data parsing
     *  dataFile         data byte as DataPart from collection
     *  inputName        name of data input
     *  IOException
     */
    private void buildDataPart(DataOutputStream dataOutputStream, DataPart dataFile, String inputName) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                inputName + "\"; filename=\"" + dataFile.getFileName() + "\"" + lineEnd);
        if (dataFile.getType() != null && !dataFile.getType().trim().isEmpty()) {
            dataOutputStream.writeBytes("Content-Type: " + dataFile.getType() + lineEnd);
        }
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dataFile.getContent());
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }

    /**
     * Simple data container use for passing byte file
     */
    public class DataPart {
        private String fileName;
        private byte[] content;
        private String type;

        /**
         * Default data part
         */
        public DataPart() {
        }

        /**
         * Constructor with data.
         *
         *  name label of data
         *  data byte data
         */
        public DataPart(String name, byte[] data) {
            fileName = name;
            content = data;
        }

        /**
         * Constructor with mime data type.
         *
         *  name     label of data
         *  data     byte data
         *  mimeType mime data like "image/jpeg"
         */
        public DataPart(String name, byte[] data, String mimeType) {
            fileName = name;
            content = data;
            type = mimeType;
        }

        /**
         * Getter file name.
         *
         *  file name
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Setter file name.
         *
         *  fileName string file name
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        /**
         * Getter content.
         *
         *  byte file data
         */
        public byte[] getContent() {
            return content;
        }

        /**
         * Setter content.
         *
         *  content byte file data
         */
        public void setContent(byte[] content) {
            this.content = content;
        }

        /**
         * Getter mime type.
         *
         *  mime type
         */
        public String getType() {
            return type;
        }

        /**
         * Setter mime type.
         *
         *  type mime type
         */
        public void setType(String type) {
            this.type = type;
        }
    }
}
