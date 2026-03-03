package com.elvis_c.rosupportcalculator.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.elvis_c.rosupportcalculator.BaseActivity
import com.elvis_c.rosupportcalculator.R
import com.elvis_c.rosupportcalculator.databinding.ActivityMainBinding
import com.elvis_c.rosupportcalculator.ui.adapter.SimpleLabelAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private val roleAdapter = SimpleLabelAdapter()
    private val hotEquipmentAdapter = SimpleLabelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.barModel = bindActionBar()
        applySystemBarInsets(binding.root)

        setupActionBar()
        setupRecyclerView()
        setupObserve()
        setupClickListener()
    }

    private fun setupObserve() {
        mainViewModel.roles.observe(this) { roles ->
            roleAdapter.submitList(roles)
        }

        mainViewModel.hotEquipments.observe(this) { equipments ->
            hotEquipmentAdapter.submitList(equipments)
        }
    }

    private fun setupRecyclerView() {
        binding.rvRoles.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = roleAdapter
        }

        binding.rvHotEquip.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = hotEquipmentAdapter
        }
    }

    private fun setupClickListener() {
        binding.btnCreateNewRole.setOnClickListener {
            mainViewModel.addNewRole()
            Toast.makeText(this, getString(R.string.main_role_added), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupActionBar() {
        showBack(false)
        showHome(false)
        showMenu(true)
    }
}
