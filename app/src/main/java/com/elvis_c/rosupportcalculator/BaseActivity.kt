package com.elvis_c.rosupportcalculator

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    val baseViewModel: BaseViewModel by viewModels()

    protected open fun isOrientationSensorEnabled(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        lockScreenOrientation(isOrientationSensorEnabled())

        setupOptionItem()
    }

    fun bindActionBar(): BaseViewModel {
        return baseViewModel
    }

    fun showBack(show: Boolean) {
        baseViewModel.setShowBack(show)
    }

    fun showHome(show: Boolean) {
        baseViewModel.setShowHome(show)
    }

    fun showMenu(show: Boolean) {
        baseViewModel.setShowMenu(show)
    }

    private fun setupOptionItem() {
        baseViewModel.optionItemEvent.observe(this) { event ->
            when (event) {
                OptionItemClick.GoBack -> handleBackPressed()
                OptionItemClick.GoHome -> finish()
                OptionItemClick.OpenMenu -> Timber.d("GoMenu")
                else -> Timber.e("Never gonna happen")
            }

        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPressed()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun lockScreenOrientation(enableSensor: Boolean) {
        requestedOrientation = if (!enableSensor) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_SENSOR
        }
    }

    protected fun applySystemBarInsets(view: android.view.View) {
        view.setOnApplyWindowInsetsListener { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = systemBars.bottom
            )
            insets
        }
    }

    protected open fun handleBackPressed() {
        finish()
    }
}