package com.kalbe.mobiledevknlibs.ErrorReporting;

/**
 * Created by Dewi Oktaviani on 1/9/2018.
 */

public class ModelError {
    private String _dtDate;
    private String _txtDeviceId;
    private String _txtFileName;

    public String get_dtDate() {
        return _dtDate;
    }
    public void set_dtDate(String _dtDate) {
        this._dtDate = _dtDate;
    }

    public String get_txtDeviceId() {
        return _txtDeviceId;
    }
    public void set_txtDeviceId(String _txtDeviceId) {
        this._txtDeviceId = _txtDeviceId;
    }
    public String get_txtFileName(){
        return _txtFileName;
    }
    public void set_txtFileName(String _txtFileName){
        this._txtFileName = _txtFileName;
    }
}
