package com.bbb.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bbb.businesscard.App
import com.bbb.businesscard.R
import com.bbb.businesscard.data.BusinessCard
import com.bbb.businesscard.databinding.ActivityAddBussinessCardBinding

class AddBusinessCardActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityAddBussinessCardBinding.inflate(layoutInflater)}

    private val mainVielModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListener()
    }
    private fun insertListener(){
        binding.btnClose.setOnClickListener {
           finish()
        }
        binding.btnSave.setOnClickListener {
        val businessCard=BusinessCard(
            nome = binding.tilName.editText?.text.toString(),
            empresa = binding.tilEmpresa.editText?.text.toString(),
            telefone = binding.tilTelefone.editText?.text.toString(),
            email = binding.tilEmail.editText?.text.toString(),
            fundoPersonalizado = binding.tilCor.editText?.text.toString()
        )
            mainVielModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success,Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}