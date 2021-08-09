package com.ecohouse.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ecohouse.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, SimpleFragment.newInstance("simple", 1), "simple1")
            .addToBackStack("simple1")
            .commitAllowingStateLoss()
    }
}