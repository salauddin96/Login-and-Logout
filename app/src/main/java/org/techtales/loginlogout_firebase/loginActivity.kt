package org.techtales.loginlogout_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtales.loginlogout_firebase.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleApliClient: GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.donHave.setOnClickListener {
            startActivity(Intent(this@loginActivity, MainActivity::class.java))
            finish()
        }

            mAuth = FirebaseAuth.getInstance()


            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.app_name))
                .requestEmail()
                .build()

        mGoogleApliClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { connectionResult ->
                    Toast.makeText(this@loginActivity, "Google Service Error", Toast.LENGTH_SHORT)
                        .show()
                }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
            binding.loginBtn.setOnClickListener {
                val email = binding.emailTxt.text.toString()
                val password = binding.passTxt.text.toString()
                signInWithEmailAndPassword(email, password)
            }

        }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this@loginActivity, home::class.java))
                    finish()
                } else {
                    Toast.makeText(this@loginActivity, "Something Wrong", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }


    override fun onStart() {
            super.onStart()
            if (Firebase.auth.currentUser != null) {
                startActivity(Intent(this@loginActivity, home::class.java))
                finish()
            }
        }

    }


