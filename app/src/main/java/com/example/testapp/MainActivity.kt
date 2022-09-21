package com.example.testapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.databinding.FragmentFirstBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private  lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val home = FirstFragment()
        val settings = SettingsFragment()
        val info = InfoFragment()


        binding.navigationBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    setCurrentFragment(home)
                    return@setOnItemSelectedListener true
                }
                R.id.action_settings -> {
                    setCurrentFragment(settings)
                    return@setOnItemSelectedListener true
                }
                R.id.action_info -> {
                    setCurrentFragment(info)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

private fun setCurrentFragment(fragment:Fragment)=
    supportFragmentManager.beginTransaction().apply {
        replace(R.id.container_fragment,fragment)
        commit()
    }
}
