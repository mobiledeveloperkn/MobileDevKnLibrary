package com.kalbe.mobiledevlibrary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kalbe.mobiledevlibrary.common.clsItem;
import com.kalbe.mobiledevlibrary.common.clsSales;

import java.sql.SQLException;

/**
 * Created by Dewi Oktaviani on 12/14/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
    private static final String DATABASE_NAME = "tesPOS.db";
    private static final int DATABASE_VERSION = 5;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    protected Dao<clsItem, Integer> mItemDao;
    protected Dao<clsSales, Integer> mSalesDao;
//    private  Dao<clsStock, Integer> mStockDao;

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, clsItem.class);
            TableUtils.createTableIfNotExists(connectionSource, clsSales.class);
//            TableUtils.createTableIfNotExists(connectionSource, clsStock.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Dao<clsItem, Integer> dao = null;
        try {
            dao = getmItemDao();
            if (oldVersion < 2) {
                // we added the age column in version 2
                onCreate(database, connectionSource);
//                dao.executeRaw("ALTER TABLE `clsItem` ADD COLUMN txtTes TEXT;");
            }
            if (oldVersion < 3) {
                // we added the weight column in version 3
                onCreate(database, connectionSource);
//                dao.executeRaw("ALTER TABLE `clsItem` ADD COLUMN txtTes2 TEXT;");
            }
            if (oldVersion < 4){
                dao.executeRaw("ALTER TABLE `clsItem` ADD COLUMN txtInsertedBy TEXT;");
            }
            if (oldVersion < 5){
                dao.executeRaw("ALTER TABLE `clsSales` ADD COLUMN txtInsertedBy TEXT;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try {
//            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
//            TableUtils.dropTable(connectionSource, clsItem.class, true);
//
//            //after we drop the old databases, we create the new ones
//            onCreate(database, connectionSource);
//        } catch (SQLException e) {
//            Log.e(DatabaseHelper.class.getName(), "Can't drop database", e);
//            throw new RuntimeException(e);
//        }
    }

    public void clearAllDataInDatabase(){
        try {
            TableUtils.createTable(connectionSource, clsItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearDataAfterLogout(){
        try {
            TableUtils.clearTable(connectionSource, clsItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<clsItem, Integer> getmItemDao() throws SQLException{
        if (mItemDao == null){
            mItemDao = getDao(clsItem.class);
        }
        return mItemDao;
    }

    public Dao<clsSales, Integer> getmSalesDao() throws SQLException{
        if (mSalesDao == null){
            mSalesDao = getDao(clsSales.class);
        }
        return mSalesDao;
    }

//    public Dao<clsStock, Integer> getmStockDao() throws SQLException{
//        if (mStockDao == null){
//            mStockDao = getDao(clsStock.class);
//        }
//        return mStockDao;
//    }

    @Override
    public void close() {
        super.close();
        mItemDao = null;
        mSalesDao = null;
    }
}
