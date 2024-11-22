package com.example.k9deliveyapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k9deliveyapp.R
import com.example.k9deliveyapp.databinding.ActivityRegisterEmailBinding
import com.example.k9deliveyapp.databinding.ActivityRegisterPasswordBinding
import org.checkerframework.checker.units.qual.A

class ActivityRegisterEmail : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterEmailBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegisterEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding.nextRegister01Button.setOnClickListener {

            val emailRegister = binding.registerEmailAddress.text.toString()

            if (emailRegister.isEmpty()) {
                Toast.makeText(this, "¡Ups! Este espacio no puede quedar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val intent = Intent(this, ActivityRegisterPassword ::class.java)
                startActivity(intent)
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}