package com.elvis_c.rosupportcalculator.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application
) : ViewModel() {

    private val _roles = MutableLiveData<List<String>>()
    val roles: LiveData<List<String>> get() = _roles

    private val _hotEquipments = MutableLiveData<List<String>>()
    val hotEquipments: LiveData<List<String>> get() = _hotEquipments

    private var newRoleIndex = 4

    init {
        _roles.value = listOf("Warlock", "Arch Bishop", "Ranger")
        _hotEquipments.value = listOf("Jupiter Crown", "Magic Crystal Boots", "Arcane Shawl")
    }

    fun addNewRole() {
        val updatedRoles = _roles.value.orEmpty().toMutableList()
        updatedRoles.add("New Role #$newRoleIndex")
        newRoleIndex += 1
        _roles.value = updatedRoles
    }


}
