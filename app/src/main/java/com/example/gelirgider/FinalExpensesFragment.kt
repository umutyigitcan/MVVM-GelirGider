package com.example.gelirgider

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gelirgider.databinding.FragmentFinalExpensesBinding

class FinalExpensesFragment : Fragment() {


    private lateinit var binding:FragmentFinalExpensesBinding
    private val viewModel:HomePageViewModel by viewModels()
    private lateinit var adapter:RVAdapter
    private lateinit var sendedData:ArrayList<RVAdapterData>
    private lateinit var mContext:Context



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_final_expenses, container, false)
        binding.finalExpensesFragmentNesnesi = this
        sendedData = ArrayList()
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.layoutManager = layoutManager
        binding.rv.setHasFixedSize(true)

        incomedData()
        adapter = RVAdapter(requireContext(), sendedData)
        binding.rv.adapter = adapter
        if (sendedData.isNotEmpty()) {
            binding.rv.scrollToPosition(sendedData.size - 1)
        }
        return binding.root
    }

    fun goHome(){
        Navigation.findNavController(requireView()).navigate(R.id.action_finalExpensesFragment_to_homePage)
    }


    fun addIncome(){
        var vt=DataSQLite(requireContext())
        val layout=layoutInflater.inflate(R.layout.alert_tasarim,null)
        val edittext=layout.findViewById(R.id.edittext) as EditText
        val ad=AlertDialog.Builder(requireContext())
        ad.setView(layout)
        ad.setTitle("Eklemek istediğiniz gelir ismini giriniz")
        ad.setPositiveButton("EKLE"){dialogInterface,i->
            var text=edittext.text.toString()
            val ad2=AlertDialog.Builder(requireContext())
            var layout2=layoutInflater.inflate(R.layout.alert_tasarim2,null)
            var edittext2=layout2.findViewById(R.id.edittext) as EditText
            ad2.setView(layout2)
            ad2.setTitle("Geliriniz ne kadar?")
            ad2.setPositiveButton("EKLE"){dialogInterface,i->
                var text2=edittext2.text.toString()
                var vt2=CurrentDataSQLite(requireContext())
                var allData=CurrentDataCommand().allData(vt2)
                for(k in allData){
                    var currentMoney=k.currentMoney.toInt()+text2.toInt()
                    var currentIncreasedMoney=k.increasedMoney.toInt()+text2.toInt()
                    CurrentDataCommand().addMoney(vt2,currentIncreasedMoney.toString())
                    CurrentDataCommand().changeMoney(vt2,currentMoney.toString())
                    DataCommand().dataAdd(vt,text2,text,currentMoney.toString(),"add")
                    binding.rv.scrollToPosition(sendedData.size-1)
                }

                var liste=DataCommand().dataAll(vt)
                for(k in liste){
                    Log.e("${k.spendingName}","${k.decreasingOrIncreasing}")
                }
                incomedData()
            }
            ad2.create().show()
        }
        ad.setNegativeButton("İPTAL"){dialogInterface,i->}

        ad.create().show()
    }
    fun missingIncome(){
        var vt=DataSQLite(requireContext())
        val layout=layoutInflater.inflate(R.layout.alert_tasarim,null)
        val edittext=layout.findViewById(R.id.edittext) as EditText
        val ad=AlertDialog.Builder(requireContext())
        ad.setView(layout)
        ad.setTitle("Eklemek istediğiniz gider ismini giriniz")
        ad.setPositiveButton("EKLE"){dialogInterface,i->
            var text=edittext.text.toString()
            val ad2=AlertDialog.Builder(requireContext())
            var layout2=layoutInflater.inflate(R.layout.alert_tasarim2,null)
            var edittext2=layout2.findViewById(R.id.edittext) as EditText
            ad2.setView(layout2)
            ad2.setTitle("Gideriniz ne kadar?")
            ad2.setPositiveButton("EKLE"){dialogInterface,i->
                var text2=edittext2.text.toString()
                var vt2=CurrentDataSQLite(requireContext())
                var allData=CurrentDataCommand().allData(vt2)
                for(k in allData){
                    var currentMoney=k.currentMoney.toInt()-text2.toInt()
                    var currentIncreasedMoney=k.missingMoney.toInt()+text2.toInt()
                    CurrentDataCommand().missingMoney(vt2,currentIncreasedMoney.toString())
                    CurrentDataCommand().changeMoney(vt2,currentMoney.toString())
                    DataCommand().dataAdd(vt,text2,text,currentMoney.toString(),"missing")
                    binding.rv.scrollToPosition(sendedData.size-1)
                }

                var liste=DataCommand().dataAll(vt)
                for(k in liste){
                    Log.e("${k.spendingName}","${k.decreasingOrIncreasing}")
                }
                incomedData()
            }
            ad2.create().show()
        }
        ad.setNegativeButton("İPTAL"){dialogInterface,i->}

        ad.create().show()
    }

    fun incomedData(){
        sendedData.clear()
        var vt=DataSQLite(requireContext())
        var dataAll=DataCommand().dataAll(vt)
        for(k in dataAll){
            sendedData.add(RVAdapterData(k.dateMonthDay,k.dateDay,k.dateTime,k.spendingName,k.decreasingOrIncreasing,k.currentMoney,k.status))
        }

    }





}