package com.bbb.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bbb.businesscard.App
import com.bbb.businesscard.databinding.ActivityMainBinding
import com.bbb.businesscard.ui.adapter.BusinessCardAdapter
import com.bbb.businesscard.util.Image

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainVielModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCards.adapter=adapter
        getAllBusinessCard()
        insertListener()
    }

    private fun insertListener() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare={card->
        Image.share(this@MainActivity,card)
        }
    }

    private fun getAllBusinessCard() {
        mainVielModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)
        }
    }
}