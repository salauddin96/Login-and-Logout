package org.techtales.loginlogout_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtales.loginlogout_firebase.databinding.ActivityHomeBinding
import org.techtales.loginlogout_firebase.databinding.ActivityMainBinding

class home : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener{
            Firebase.auth.signOut()
            startActivity(Intent(this@home, MainActivity::class.java))
            finish()
        }
    }
}