package com.example.gelirgider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataSQLite(mContext: Context):SQLiteOpenHelper(mContext,"Data",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Data(dateMonthDay TEXT ,dateDay TEXT,dateTime TEXT,spendingName TEXT, " +
                "decreasingOrIncreasing TEXT,currentMoney TEXT,status TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Data",null)
        onCreate(db)
    }
}