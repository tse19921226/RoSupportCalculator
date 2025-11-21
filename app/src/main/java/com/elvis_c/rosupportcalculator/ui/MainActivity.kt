package com.elvis_c.rosupportcalculator.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elvis_c.rosupportcalculator.BaseActivity
import com.elvis_c.rosupportcalculator.R
import com.elvis_c.rosupportcalculator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.barModel = bindActionBar()

        setupActionBar()
    }

    fun setupActionBar() {
//        showBack(false)
//        showHome(false)
//        showMenu(true)
    }

}