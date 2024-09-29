package com.example.gelirgider

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(var mContext:Context,var incomingData:ArrayList<RVAdapterData>):RecyclerView.Adapter<RVAdapter.cardViewItemHolder>() {
    inner class cardViewItemHolder(view:View):RecyclerView.ViewHolder(view){
        var dateMonthDay:TextView
        var dateDay:TextView
        var dateTime:TextView
        var spendingName:TextView
        var decreasingOrIncreasing:TextView
        var currentMoney:TextView
        init {
            dateMonthDay=view.findViewById(R.id.dateMonthDay)
            dateDay=view.findViewById(R.id.dateDay)
            dateTime=view.findViewById(R.id.dateTime)
            spendingName=view.findViewById(R.id.spendingName)
            decreasingOrIncreasing=view.findViewById(R.id.decreasingOrIncreasing)
            currentMoney=view.findViewById(R.id.currentMoney)
        }
    }

    override fun onBindViewHolder(holder: cardViewItemHolder, position: Int) {
        var k=incomingData[position]
        if(k.status=="add"){
            holder.spendingName.text=k.spendingName
            holder.currentMoney.text=k.currentMoney
            holder.dateMonthDay.text=k.dateMonthDay
            holder.dateDay.text=k.dateDay
            holder.decreasingOrIncreasing.text="+ "+k.decreasingOrIncreasing+" TL"
            holder.dateTime.text=k.dateTime
        }
        if(k.status!="add"){
            holder.spendingName.text=k.spendingName
            holder.currentMoney.text=k.currentMoney
            holder.dateMonthDay.text=k.dateMonthDay
            holder.dateDay.text=k.dateDay
            holder.decreasingOrIncreasing.text="- "+k.decreasingOrIncreasing+" TL"
            holder.dateTime.text=k.dateTime
            holder.decreasingOrIncreasing.setTextColor(Color.RED)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cardViewItemHolder {
        val layout=LayoutInflater.from(mContext).inflate(R.layout.cardview,parent,false)
        return cardViewItemHolder(layout)
    }

    override fun getItemCount(): Int {
        return incomingData.size
    }
}