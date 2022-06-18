package com.example.spark

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.spark.databinding.ActivitySerialNumberBinding
import com.google.firebase.auth.FirebaseAuth

class SerialNumber : AppCompatActivity() {

    private lateinit var binding: ActivitySerialNumberBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySerialNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var extras =

        firebaseAuth = FirebaseAuth.getInstance()

        binding.serialSubmitBtn.setOnClickListener {
            var serialNumber = binding.serialNumberTextInput.toString()


            val email: String? = intent.getStringExtra("Email")
            var pass: String? = intent.getStringExtra("Pass")

           if (pass == null){
               pass = ""
           }

            if (email != null) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        //TODO: Store these values in local db as well
                        //TODO: Send serial number to database and link with the e-mail if possible
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }



}