package com.entry.asqliteteaching;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TeachDBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "SQLite_Local.db";
    public static final String TABLE_USER = "User";

    public TeachDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override// 初始化时调用 如果不存在时建立表
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table Orders(Id integer primary key, CustomName text, OrderPrice integer, Country text);
        String sql = "create table if not exists " + TABLE_USER + " (Id integer primary key, Name text, Password text, Age integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override// 更新修改数据库结构、表结构是调用该方法进行修改（修改代码写在这里）
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_USER;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

}
