package com.geowind.zhangwen.sqlitetest.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by zhangwen on 2016/11/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "hunong.db";//数据库名
    public static final int DB_VERSION = 1;//数据库版本
    public static final String TASK_TABLE_NAME ="task" ;//表名
    public static final String UNMAE = "uname";
    public static final String FNAME = "fUname";
    public static final String FNO = "fno";
    public  static final  String ID="_id";
    public static final String WORKLOAD = "workLoad";
    public static final String MNO = "mno";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String STATE ="state" ;
    public static final String FZNO = "fzno";
    public static final String FAREA = "farea";
    public static final String FADDR = "faddr";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String FPIC = "fpic";
    public static final String CROPTYPE = "cropType";
    public static final String MSTYLE = "mstyle";
    public static final String NOTE = "note";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        String  sql="CREATE TABLE "+TASK_TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                UNMAE+" VARCHAR(10), "+FNAME+" VARCHAR(10)  )";
        db.execSQL(sql);
        /**
         * ,+FNO+" INT," +
         WORKLOAD+" VARCHAR(10), "+MNO+" VARCHAR(20), "+TYPE+" VARCHAR(10), "+DATE+" VARCHAR(20), "+STATE+" VARCHAR(5), "+FZNO+" VARCHAR(20)," +
         FAREA+" DOUBLE,"+FADDR+" VARCHAR(30),"+LONGITUDE+" DOUBLE,"+LATITUDE+" DOUBLE,"+FPIC+
         " VARCHAR(50),"+CROPTYPE+" VARCHAR(10) ,"+MSTYLE+" VARCHAR(10),"+NOTE+" VARCHAR(200))"
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       //更新表
    }
}
