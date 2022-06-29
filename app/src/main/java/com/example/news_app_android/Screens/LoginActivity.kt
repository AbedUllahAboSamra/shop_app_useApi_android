package com.example.news_app_android.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.news_app_android.Fragment.bottomDialogFragment
import com.example.news_app_android.MainActivity
import com.example.news_app_android.R
import com.example.news_app_android.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var aaa = bottomDialogFragment()
        aaa.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDilogeTheme2)
        aaa.show(supportFragmentManager, "SAD")
        aaa.isCancelable = true

binding.btnSkip.setOnClickListener{
    startActivity(
        Intent(
            this,
            MainActivity::class.java
        )
    )
    finish()

}
// plus code
        binding.btnGoogleLogin.setOnClickListener {
        }
        binding.btnFaceBookLogin.setOnClickListener {

        }
        binding.btnAppleLogin.setOnClickListener {
        }
// end plus code


        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLoginwithEmailAndPass.setOnClickListener {
            aaa.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDilogeTheme)

            aaa.show(supportFragmentManager, "SAD")

        }


    }
}
