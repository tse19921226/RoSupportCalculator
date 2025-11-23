package com.elvis_c.rosupportcalculator.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elvis_c.rosupportcalculator.BaseActivity
import com.elvis_c.rosupportcalculator.R
import com.elvis_c.rosupportcalculator.databinding.ActivityLogoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LogoActivity : BaseActivity() {
    private lateinit var binding: ActivityLogoBinding
    private lateinit var logoViewModel: LogoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_logo)

        logoViewModel = ViewModelProvider(this)[LogoViewModel::class.java]
        binding.lifecycleOwner = this
        applySystemBarInsets(binding.root)
    }

    override fun onResume() {
        super.onResume()
        testFlow()
    }

    fun startMainActivity() {
        Timber.d("start MainActivity")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun testFlow() {
        lifecycleScope.launch {
            delay(500)
            startMainActivity()
        }
    }
}