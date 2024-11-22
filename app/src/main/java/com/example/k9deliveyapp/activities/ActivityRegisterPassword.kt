package com.example.k9deliveyapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k9deliveyapp.R
import com.example.k9deliveyapp.databinding.ActivityRegisterPasswordBinding

class ActivityRegisterPassword : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding.nextRegister02Button.setOnClickListener {

            val password = binding.registerPassword.text.toString()
            val confirmPassword = binding.ConfirmRegisterPassword.text.toString()

            if (password.isEmpty()||confirmPassword.isEmpty())
            {
                Toast.makeText(this, "¡Ups! Parece que olvidaste llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(password != confirmPassword)
            {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
            if(password.length<6 || confirmPassword.length<6)
            {
                Toast.makeText(this, "La contraseña debe tener como minimo 6 caracteres", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, ActivityResgisterName::class.java)
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