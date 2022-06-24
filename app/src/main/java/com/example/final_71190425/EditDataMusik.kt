package com.example.final_71190425

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class EditDataMusik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musik_edit)

        val judulLagu = getIntent().getStringExtra("judulLagu")
        val namaPenyanyi = getIntent().getStringExtra("namaPenyanyi")
        val judulAlbum = getIntent().getStringExtra("judulAlbum")
        val genre = getIntent().getStringExtra("genre")
        val tahunRilis = getIntent().getStringExtra("tahunRilis")

        val judulLagunya = findViewById<TextView>(R.id.tv_judulLagu)
        judulLagunya.setText("${judulLagu}")

        val namaPenyanyinya = findViewById<EditText>(R.id.et_namaPenyanyi)
        namaPenyanyinya.setText("${namaPenyanyi}")

        val judulAlbumnya = findViewById<EditText>(R.id.et_judulAlbum)
        judulAlbumnya.setText("${judulAlbum}")

        val genrenya = findViewById<EditText>(R.id.et_genre)
        genrenya.setText("${genre}")

        val tahunRilisnya = findViewById<EditText>(R.id.et_tahunRilis)
        tahunRilisnya.setText("${tahunRilis}")

        val btnEdit = findViewById<Button>(R.id.bt_edit)
        val btnBack = findViewById<Button>(R.id.bt_back)
        val firestore = FirebaseFirestore.getInstance()

        btnEdit.setOnClickListener {
            firestore.collection("BankLagu")
                .whereEqualTo("JudulLagu", judulLagunya.text.toString())
                .get()
                .addOnSuccessListener {
                    for (document in it){

                        firestore.collection("BankLagu").document(document.id)
                            .update("NamaPenyanyi",namaPenyanyinya.text.toString(),"JudulAlbum",judulAlbumnya.text.toString(),"Genre",genrenya.text.toString(), "TahunRilis",tahunRilisnya.text.toString())
                            .addOnSuccessListener {
                                Toast.makeText(this,"Data berhasil diubah",Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener{
                    Log.d("Gagal", "Data gagal diubah")
                }
        }

        btnBack.setOnClickListener {
            val i = Intent(this, LihatDataMusik::class.java)
            startActivity(i)
        }
    }
}