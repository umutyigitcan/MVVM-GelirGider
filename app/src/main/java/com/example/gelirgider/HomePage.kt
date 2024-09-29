package com.example.gelirgider

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.gelirgider.databinding.FragmentHomePageBinding

class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()
    private lateinit var vt: CurrentDataSQLite
    private var currentMoney2 = 0f
    private var add = 0f
    private var missing = 0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vt = CurrentDataSQLite(requireContext())
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        binding.homePageFragmentNesnesi = this
        binding.lifecycleOwner = this

        getDataFromDatabase()
        currentMoney()

        binding.spendMoneyButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homePage_to_finalExpensesFragment)
        }


        setupDonutChart()

        return binding.root
    }

    private fun getDataFromDatabase() {
        val getData = CurrentDataCommand().allData(vt)
        for (k in getData) {
            currentMoney2 = k.currentMoney.toFloat()
            add = k.increasedMoney.toFloat()
            missing = k.missingMoney.toFloat()
        }
    }

    private fun setupDonutChart() {
        val total = currentMoney2 + add + missing
        val donutValues = mutableListOf<Float>()

        if (total > 0) {
            donutValues.add((currentMoney2 / total) * 100)
            donutValues.add((add / total) * 100)
            donutValues.add((missing / total) * 100)
        } else {
            donutValues.add(0f)
            donutValues.add(0f)
            donutValues.add(0f)
        }


        binding.apply {
            donutChart.donutColors = intArrayOf(
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#FFE64A19"),
                Color.parseColor("#FFAFB42B")
            )
            donutChart.animation.duration = AnimationConstants.animationDuration


            Log.d("DonutValues", donutValues.toString())


            donutChart.animate(donutValues)
        }
    }



    object AnimationConstants {
        const val animationDuration = 3000L
    }

    private fun currentMoney() {
        binding.currentDebt.text = "Toplam Eksilen Para: $missing TL"
        binding.currentMoney.text = "Güncel Para: $currentMoney2 TL"
        binding.monthlyIncome.text = "Toplam Artan Para: $add TL"
    }
}
