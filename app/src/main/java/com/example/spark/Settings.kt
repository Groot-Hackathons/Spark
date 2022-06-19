package com.example.spark

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.spark.databinding.SettingsBinding
import com.google.firebase.auth.FirebaseAuth

class Settings: AppCompatActivity() {

    private lateinit var binding: SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var firebaseAuth = FirebaseAuth.getInstance()
        var currentUser = firebaseAuth.getCurrentUser()
        var email = currentUser?.email
        binding.userName.setText(email.toString())


        binding.logout.setOnClickListener {
            var firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()

            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }

}