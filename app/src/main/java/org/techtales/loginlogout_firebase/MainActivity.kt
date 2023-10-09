package org.techtales.loginlogout_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtales.loginlogout_firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.regButton.setOnClickListener{
            if (binding.emailTxt.text.toString() == "" || binding.passTxt.text.toString() == "") {
                Toast.makeText(
                    this@MainActivity,
                    "Please enter all the information...",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Firebase.auth.createUserWithEmailAndPassword(
                    binding.emailTxt.text.toString(),
                    binding.passTxt.text.toString()
                )
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            startActivity(Intent(this@MainActivity, home::class.java))
                            finish()
                        }else{
                            Toast.makeText(
                                this@MainActivity,
                                it.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser!=null){
            startActivity(Intent(this@MainActivity, home::class.java))
            finish()
        }
    }
}