package com.example.gelirgider

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CurrentDataSQLite(mContext:Context):SQLiteOpenHelper(mContext,"currentData",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE currentData(currentMoney TEXT,missingMoney TEXT,increasedMoney TEXT);")
        db.execSQL("INSERT INTO currentData(currentMoney,missingMoney,increasedMoney) VALUES('0','0','0')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS currentData",null)
        onCreate(db)
    }
}