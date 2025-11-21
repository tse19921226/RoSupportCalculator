package com.elvis_c.rosupportcalculator

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    val baseViewModel: BaseViewModel by viewModels()

    fun bindActionBar(): BaseViewModel {
        return baseViewModel
    }

//    fun showBack(show: Boolean) {
//        baseViewModel.setShowBack(show)
//    }
//
//    fun showHome(show: Boolean) {
//        baseViewModel.setShowHome(show)
//    }
//
//    fun showMenu(show: Boolean) {
//        baseViewModel.setShowMenu(show)
//    }
}