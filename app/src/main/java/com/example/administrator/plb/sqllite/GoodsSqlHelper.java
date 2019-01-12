package com.example.administrator.plb.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoodsSqlHelper extends SQLiteOpenHelper{
    public static String dbName="GoodsSql.db";
    public static String GoodsTable="Goods";
    public static String ClassTable="classes";
    public GoodsSqlHelper(Context context) {
        super(context, dbName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE classes\n" +
                "(\n" +
                "className VARCHAR(20),\n" +
                "note TEXT\n" +
                ")");
        db.execSQL("CREATE TABLE goods\n" +
                "(\n" +
                "GoodsName VARCHAR(20),\n" +
                "GoodsClass VARCHAR(20),\n" +
                "GoodsImage TEXT,\n" +
                "GoodsPrice INT,\n" +
                "GoodsUnit VARCHAR(10),\n" +
                "inventory INT,\n" +
                "minCount INT,\n" +
                "sellingTime DATETIME,\n" +
                "FOREIGN KEY(GoodsClass) REFERENCES classes(className)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
