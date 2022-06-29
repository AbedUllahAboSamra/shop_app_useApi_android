package com.example.news_app_android.Screens

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.news_app_android.MainActivity
import com.example.news_app_android.classs.UserModle
import com.example.news_app_android.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class SignUpActivity : AppCompatActivity() {


    private val reqPickImageCode = 4150
    lateinit var binding: ActivitySignUpBinding

    var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // defintion
        var db = Firebase.firestore
        val stroge = Firebase.storage
        val strogeRef = stroge.reference.child("images")
        var auth = FirebaseAuth.getInstance()
        //


        binding.btnRigester.setOnClickListener {
           var name = binding.fname.text
            var lastname = binding.lname.text
            var email = binding.edtEmail.text
            var password = binding.edtPassword.text
            if (name != null && lastname != null && email != null && password != null && email.contains(
                    "@"
                ) && password.length >= 6 && email.contains(".")
            ) {
                if (imageUri != null) {
                    binding.progressBar.visibility= View.VISIBLE

                    val bitmap = (binding.imagePrifile.drawable as BitmapDrawable).bitmap
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
                    val data = baos.toByteArray()

                    val childref =
                        strogeRef.child(System.currentTimeMillis().toString() + "ASDImages")

                    var uploadTask = childref.putBytes(data)
                    uploadTask.addOnFailureListener {
                        Toast.makeText(this, "cheek your network connect", Toast.LENGTH_SHORT)
                            .show()

                    }.addOnSuccessListener { taskSnapshot ->




                        childref.downloadUrl.addOnSuccessListener {ittt->

                             auth.createUserWithEmailAndPassword(
                                email.toString(),
                                password.toString()
                            ).addOnFailureListener {
                                Toast.makeText(
                                    this,
                                    "cheek your network connect",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                            }

                                .addOnSuccessListener {

                                     UserModle.userModle=UserModle(
                                        userName = name.toString() + "" + lastname.toString(),
                                        email = email.toString(),
                                        password = password.toString(),
                                        uid = it.user!!.uid,
                                        imageUrl = ittt.toString()
                                    )
                                    db.collection("users")
                                        .document(it.user!!.uid)
                                        .set(UserModle.userModle!!.toJson())
                                        .addOnSuccessListener {
                                            startActivity(Intent(this,MainActivity::class.java))
                                            finish()
                                            binding.progressBar.visibility= View.INVISIBLE

                                        }.addOnFailureListener {
                                            binding.progressBar.visibility= View.INVISIBLE

                                        }

                                }

                        }

                    }

                } else {
                    Toast.makeText(this, "Pick your image Profile", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "fields please..", Toast.LENGTH_SHORT).show()
            }


        }


        //pickImage

        binding.btnBickImge.setOnClickListener {
            intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, reqPickImageCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == reqPickImageCode && resultCode == RESULT_OK) {
            imageUri = data?.data
            if (imageUri != null) {
                binding.imagePrifile.setImageURI(imageUri)
            }
        }

    }

}