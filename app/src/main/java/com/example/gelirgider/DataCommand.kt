package com.example.gelirgider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataCommand {


    fun dataAdd(vt:DataSQLite,decreasingOrIncreasing:String,spendingName:String,currentMoney:String,status:String){
        var db=vt.writableDatabase
        val currentDataTime=LocalDateTime.now()
        val onlyMonthDay=DateTimeFormatter.ofPattern("dd")
        val onlyMonth=DateTimeFormatter.ofPattern("MM")
        val dateTime=DateTimeFormatter.ofPattern("HH:mm")
        var month=""
        val onlyMonthDayString=currentDataTime.format(onlyMonthDay)
        val onlyMonthString=currentDataTime.format(onlyMonth)
        val dateTimeString=currentDataTime.format(dateTime)
        when(onlyMonthString){
            "01"-> month="OCK"
            "02"->month="ŞUB"
            "03"->month="MAR"
            "04"-> month="NİS"
            "05"->month="MAY"
            "06"->month="HAZ"
            "07"-> month="TEM"
            "08"->month="AGU"
            "09"->month="EYL"
            "10"-> month="EKİ"
            "11"->month="KAS"
            "12"->month="ARA"
        }
        db.execSQL("INSERT INTO Data(dateMonthDay,dateDay,dateTime,spendingName,decreasingOrIncreasing,currentMoney,status) VALUES('$onlyMonthDayString','$month','$dateTimeString','$spendingName','$decreasingOrIncreasing','$currentMoney','$status')")
        db.close()
    }





    fun dataAll(vt:DataSQLite):ArrayList<Data>{
        var db=vt.writableDatabase
        var dataList=ArrayList<Data>()
        var cursor=db.rawQuery("SELECT * FROM Data",null)
        while (cursor.moveToNext()){
            var data=Data(cursor.getString(cursor.getColumnIndexOrThrow("dateMonthDay")),
                cursor.getString(cursor.getColumnIndexOrThrow("dateDay")),
                cursor.getString(cursor.getColumnIndexOrThrow("dateTime")),
                cursor.getString(cursor.getColumnIndexOrThrow("spendingName")),
                cursor.getString(cursor.getColumnIndexOrThrow("decreasingOrIncreasing")),
                cursor.getString(cursor.getColumnIndexOrThrow("currentMoney")),
                cursor.getString(cursor.getColumnIndexOrThrow("status")))
            dataList.add(data)
        }
        return dataList
        db.close()
    }

    fun dataClear(vt:DataSQLite){
        var db=vt.writableDatabase
        db.execSQL("DELETE FROM Data")
        db.close()
    }



}