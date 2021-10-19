package com.example.qrcodemarket.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.qrcodemarket.R
import com.example.qrcodemarket.data.network.SyntheticApi
import com.example.qrcodemarket.data.respositories.StatisticalRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigation.background = null
        floatingActionButton.setOnClickListener {
            bottomNavigation?.selectedItemId = R.id.scanFragment
        }
        val navController = findNavController(R.id.fragment)
        bottomNavigation.setupWithNavController(navController)

    }
}