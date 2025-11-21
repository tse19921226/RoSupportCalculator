package com.elvis_c.rosupportcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class OptionItemClick{
    data object GoHome: OptionItemClick()
    data object GoBack: OptionItemClick()
    data object OpenMenu: OptionItemClick()
}

class BaseViewModel : ViewModel() {

    private val _optionItem = MutableLiveData<OptionItemClick>()
    val optionItemEvent: LiveData<OptionItemClick> get() = _optionItem

//    val showBack = MutableLiveData<Boolean>().apply { value = false }
//    fun setShowBack(show: Boolean) {
//        showBack.value = show
//    }
//
//    val showHome = MutableLiveData<Boolean>().apply { value = false }
//    fun setShowHome(show: Boolean) {
//        showHome.value = show
//    }
//
//    val showMenu = MutableLiveData<Boolean>().apply { value = false }
//    fun setShowMenu(show: Boolean) {
//        showMenu.value = show
//    }
//
//    fun onBackClick() {
//        _optionItem.value = OptionItemClick.GoBack
//    }
//
//    fun onHomeClick() {
//        _optionItem.value = OptionItemClick.GoHome
//    }
//
//    fun onMenuClick() {
//        _optionItem.value = OptionItemClick.OpenMenu
//    }

}