package com.kalbe.mobiledevlibrary.common;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Dewi Oktaviani on 12/21/2017.
 */

@DatabaseTable
public class clsSales implements Serializable{
    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public String getTxtItemCode() {
        return txtItemCode;
    }

    public void setTxtItemCode(String txtItemCode) {
        this.txtItemCode = txtItemCode;
    }

    public String getTxtItemName() {
        return txtItemName;
    }

    public void setTxtItemName(String txtItemName) {
        this.txtItemName = txtItemName;
    }

    public String getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice = txtPrice;
    }

    public String getTxtCategory() {
        return txtCategory;
    }

    public void setTxtCategory(String txtCategory) {
        this.txtCategory = txtCategory;
    }

    public String getTxtJmlItem() {
        return txtJmlItem;
    }

    public void setTxtJmlItem(String txtJmlItem) {
        this.txtJmlItem = txtJmlItem;
    }

    public String getTxtTotalItem() {
        return txtTotalItem;
    }

    public void setTxtTotalItem(String txtTotalItem) {
        this.txtTotalItem = txtTotalItem;
    }

    @DatabaseField(generatedId = true, columnName = "salesId")
    private int salesId;
    @DatabaseField (columnName = "txtItemCode")
    private String txtItemCode;
    @DatabaseField (columnName = "txtItemName")
    private String txtItemName;
    @DatabaseField (columnName = "txtPrice")
    private String txtPrice;
    @DatabaseField (columnName = "txtCategory")
    private String txtCategory;
    @DatabaseField (columnName = "txtJmlItem")
    private String txtJmlItem;
    @DatabaseField (columnName = "txtTotalItem")
    private String txtTotalItem;

    public String getTxtInsertedBy() {
        return txtInsertedBy;
    }

    public void setTxtInsertedBy(String txtInsertedBy) {
        this.txtInsertedBy = txtInsertedBy;
    }

    @DatabaseField (columnName = "txtInsertedBy")
    private String txtInsertedBy;

}
