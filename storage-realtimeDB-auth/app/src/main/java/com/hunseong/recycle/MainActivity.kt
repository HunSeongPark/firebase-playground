package com.hunseong.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hunseong.recycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun initNavigation() = with(binding) {
        val navController =
            (supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment).navController

        // 메뉴 제외 바텀 바 gone
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.home_fragment ||
                destination.id == R.id.chat_fragment ||
                destination.id == R.id.my_fragment) {
                bottomNav.isVisible = true
            } else {
                bottomNav.isGone = true
            }
        }

        bottomNav.setupWithNavController(navController)
    }
}