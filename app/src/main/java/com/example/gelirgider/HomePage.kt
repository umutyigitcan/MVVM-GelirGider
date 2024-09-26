package com.example.gelirgider

import android.database.DatabaseUtils
import android.graphics.Color
import android.os.Bundle
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
    private val viewModel:HomePageViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home_page,container,false)
        binding.homePageFragmentNesnesi=this

        binding.homeButton.setOnClickListener { Navigation.findNavController(it).navigate(R.id.action_homePage_to_finalExpensesFragment) }
        binding.apply {
            donutChart.donutColors= intArrayOf(
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#FFE64A19"),
                Color.parseColor("#FFAFB42B")
            )
            donutChart.animation.duration = animationDuration
            donutChart.animate(donutSet)
        }
        return binding.root
    }
    companion object {

        private val donutSet = listOf(
            20f,
            40f,
            40f
        )

        private const val animationDuration = 1000L
    }
}