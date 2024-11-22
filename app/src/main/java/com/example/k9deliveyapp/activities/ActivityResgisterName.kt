package com.example.k9deliveyapp.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.k9deliveyapp.databinding.ActivityRegisterEmailBinding
import com.example.k9deliveyapp.databinding.ActivityRegisterPasswordBinding
import com.example.k9deliveyapp.databinding.ActivityResgisterNameBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ActivityResgisterName : AppCompatActivity() {

    private lateinit var binding: ActivityResgisterNameBinding
    private lateinit var binding01: ActivityRegisterEmailBinding
    private lateinit var binding02: ActivityRegisterPasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResgisterNameBinding.inflate(layoutInflater)
        binding01 = ActivityRegisterEmailBinding.inflate(layoutInflater)
        binding02 = ActivityRegisterPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.nextRegister03Button.setOnClickListener {
            val nombre = binding.registerName.text.toString()
            val apellido = binding.registerLastName.text.toString()
            val email = binding01.registerEmailAddress.text.toString()
            val password = binding02.registerPassword.text.toString()



            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Debug", "Usuario creado exitosamente")
                        insertarInfoBd(nombre, apellido,email,password)
                    } else {
                        Log.e("Debug", "Error al crear usuario: ${task.exception?.message}")
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun insertarInfoBd( nombre: String, apellido: String, email: String, password: String){
        progressDialog.setMessage("Guardando inofrmacion...")

        val uidBd = FirebaseAuth.getInstance().uid

        val nombreBd = nombre
        val apellidoBd = apellido
        val emailBd = email
        val passwordBd = password
        val tiempoBd = Constant().obtenerTiempoDispositivo()

        val datosUsuario = HashMap<String,Any>()
        datosUsuario["uid"] = "$uidBd"
        datosUsuario["nombre"] = "$nombreBd"
        datosUsuario["apellido"] = "$apellidoBd"
        datosUsuario["email"] = "$emailBd"
        datosUsuario["password"] = "$passwordBd"
        datosUsuario["tiempo"] = tiempoBd

        val db = FirebaseDatabase.getInstance().getReference("Usuarios")
        db.child(uidBd!!)
            .setValue(datosUsuario)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {e->
                progressDialog.dismiss()
            }

    }
}
