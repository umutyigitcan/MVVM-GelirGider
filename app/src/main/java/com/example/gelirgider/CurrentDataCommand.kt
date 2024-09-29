package com.example.gelirgider

class CurrentDataCommand {
    fun changeMoney(vt:CurrentDataSQLite,currentMoney:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE currentData SET currentMoney='$currentMoney'")
        db.close()
    }

    fun addMoney(vt:CurrentDataSQLite,addMoney:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE currentData SET increasedMoney='$addMoney'")
        db.close()
    }

    fun missingMoney(vt:CurrentDataSQLite,missingMoney:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE currentData SET missingMoney='$missingMoney'")
    }

    fun allData(vt:CurrentDataSQLite):ArrayList<CurrentData>{
        var db=vt.writableDatabase
        var dataList=ArrayList<CurrentData>()
        var cursor=db.rawQuery("SELECT * FROM currentData",null)
        while (cursor.moveToNext()){
            var allData=CurrentData(cursor.getString(cursor.getColumnIndexOrThrow("currentMoney")),
                cursor.getString(cursor.getColumnIndexOrThrow("missingMoney")),
                cursor.getString(cursor.getColumnIndexOrThrow("increasedMoney")))
            dataList.add(allData)
        }
        return dataList
    }


}