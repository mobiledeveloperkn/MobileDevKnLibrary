package com.kalbe.mobiledevlibrary.repo;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.kalbe.mobiledevlibrary.DatabaseHelper;
import com.kalbe.mobiledevlibrary.DatabaseManager;
import com.kalbe.mobiledevlibrary.common.clsSales;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dewi Oktaviani on 12/21/2017.
 */

public class clsSalesRepo implements crud {
    private DatabaseHelper helper;

    public clsSalesRepo (Context context){
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) throws SQLException {
        int index = -1;
        clsSales object = (clsSales) item;
        try {
            index = helper.getmSalesDao().create(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int createOrUpdate(Object item) throws SQLException {
        int index = -1;
        clsSales object = (clsSales) item;
        try {
            Dao.CreateOrUpdateStatus status = helper.getmSalesDao().createOrUpdate(object);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Object item) throws SQLException {
        int index = -1;
        clsSales object = (clsSales) item;
        try {
            index = helper.getmSalesDao().update(object);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public int delete(Object item) throws SQLException {
        int index = -1;
        clsSales object = (clsSales) item;
        try {
            index = helper.getmSalesDao().delete(object);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return index;
    }

    @Override
    public Object findById(int id) throws SQLException {
        clsSales item = null;
        try {
            item = helper.getmSalesDao().queryForId(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public List<?> findAll() throws SQLException {
        List<clsSales> item = null;
        try {
            item = helper.getmSalesDao().queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }
}
