package com.example.news_app_android.Fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.news_app_android.MainActivity
import com.example.news_app_android.classs.UserModle
import com.example.news_app_android.databinding.BottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase

class bottomDialogFragment() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = BottomsheetBinding.inflate(inflater, container, false)
//        var auth = FirebaseAuth.getInstance()
//        var db = Firebase.firestore

//        binding.logon.setOnClickListener {
//            var email = binding.edtEmail.text.toString()
//            var passwrod = binding.edtPassword.text.toString()
//            var boo = binding.btnSafeLogin.isChecked
//
//            if (email != null && passwrod != null) {
//
//                if (email.contains("@") && email.contains(".") && passwrod.length >= 6) {
//                    auth.signInWithEmailAndPassword(email, passwrod)
//                        .addOnSuccessListener { it ->
//                            if(boo){
//                                var sharedPef =
//                                    requireActivity().getSharedPreferences("MY", MODE_PRIVATE)
//                                var editor = sharedPef.edit()
//                                editor.putString("uid", it.user!!.uid)
//                                editor.apply()
//                            }
//                            db.collection("users")
//                                .document(it.user!!.uid).get().addOnSuccessListener { it ->
//
//                                    UserModle.userModle.userName = it.get("userName") as String
//                                    UserModle.userModle.uid = it.get("uid").toString()
//                                    UserModle.userModle.password = it.get("password").toString()
//                                    UserModle.userModle.email = it.get("email").toString()
//                                    UserModle.userModle.imageUrl = it.get("imageUrl").toString()
//
//                                    startActivity(
//                                        Intent(
//                                            requireContext(),
//                                            MainActivity::class.java
//                                        )
//                                    )
//                                    requireActivity().finish()
//
//                                }
//
//
//                        }
//                        .addOnFailureListener {
//                            Toast.makeText(
//                                requireContext(),
//                                "Incorrect email or password",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Incorrect email or password",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//
//        }
        return binding.root
    }

}