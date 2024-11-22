package com.example.k9deliveyapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k9deliveyapp.R
import com.example.k9deliveyapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class ActivityLogin : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= Firebase.auth

        binding.loginButton.setOnClickListener {

            val email = binding.loginEmailAddress.text.toString()
            val password  = binding.loginPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "¡Ups! Parece que olvidaste llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                       val toast = Toast.makeText(this,"¡Datos correctos!", Toast.LENGTH_SHORT)
                        toast.show()
                        val intent= Intent(this, ActivityReceta01:: class.java)
                        startActivity(intent)
                    } else {
                        val toast = Toast.makeText(this,"¡Datos Incorrectos! $email - $password", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }

        }
        binding.registerButton.setOnClickListener {
            val intent= Intent(this, ActivityRegisterEmail:: class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(/* view = */ findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }




}