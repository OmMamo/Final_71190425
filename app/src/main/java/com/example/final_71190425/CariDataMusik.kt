package com.example.final_71190425

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class CariDataMusik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_data_musik)



        val nama = findViewById<EditText>(R.id.et_namaPenyanyi)

        val judulLagu = findViewById<TextView>(R.id.tv_judulLagu)
        val namaPenyanyi = findViewById<TextView>(R.id.tv_namaPenyanyi)
        val judulAlbum = findViewById<TextView>(R.id.tv_judulAlbum)
        val genre= findViewById<TextView>(R.id.tv_genre)
        val tahunRilis= findViewById<TextView>(R.id.tv_tahunRilis)


        val btnCari = findViewById<Button>(R.id.bt_cari)
        val btnBack = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnCari.setOnClickListener {
            firestore.collection("BankLagu")
                .whereEqualTo("NamaPenyanyi", nama.text.toString())
                .get()
                .addOnSuccessListener {

                    for (document in it){
                        judulLagu.setText("${document.data["JudulLagu"]}")
                        namaPenyanyi.setText("${document.data["NamaPenyanyi"]}")
                        judulAlbum.setText("${document.data["JudulAlbum"]}")
                        genre.setText("${document.data["Genre"]}")
                        tahunRilis.setText("${document.data["TahunRilis"]}")
                    }
                    Toast.makeText(this,"Data Ditemukan!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Log.d("Gagal", "Data tidak ditemukan!")
                }
        }

        btnBack.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

    }
}