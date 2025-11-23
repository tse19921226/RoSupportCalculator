package com.elvis_c.rosupportcalculator.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elvis_c.rosupportcalculator.BaseActivity
import com.elvis_c.rosupportcalculator.OptionItemClick
import com.elvis_c.rosupportcalculator.R
import com.elvis_c.rosupportcalculator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.barModel = bindActionBar()
        applySystemBarInsets(binding.root)

        setupActionBar()
        setupObserve()
    }

    private fun setupObserve() {

    }

    fun setupActionBar() {
        showBack(false)
        showHome(false)
        showMenu(true)
    }

}