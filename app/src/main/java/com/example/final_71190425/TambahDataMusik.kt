package com.example.final_71190425

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahDataMusik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambahdatamusik)

        val judullagu = findViewById<EditText>(R.id.tv_judulLagu)
        val namapenyanyi= findViewById<EditText>(R.id.et_namaPenyanyi)
        val judulalbum = findViewById<EditText>(R.id.et_judulAlbum)
        val genree = findViewById<EditText>(R.id.et_genre)
        val tahunrilis= findViewById<EditText>(R.id.et_tahunRilis)

        val btnsubmit = findViewById<Button>(R.id.bt_submit)
        val btnback = findViewById<Button>(R.id.bt_back)

        val firestore = FirebaseFirestore.getInstance()

        btnback.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java);
            startActivity(i)
        }

        btnsubmit.setOnClickListener {
            if (judullagu.text.toString() != "" && namapenyanyi.text.toString() != "" && judulalbum.text.toString() != "" && genree.text.toString() != "" && tahunrilis.text.toString() != "") {

                val musik = Musik(
                    judullagu.text.toString(),
                    namapenyanyi.text.toString(),
                    judulalbum.text.toString(),
                    genree.text.toString(),
                    tahunrilis.text.toString()

                )
                firestore.collection("BankLagu").add(musik)
                Toast.makeText(this, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

                judullagu.setText("")
                namapenyanyi.setText("")
                judulalbum.setText("")
                genree.setText("")
                tahunrilis.setText("")
            } else {
                Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_LONG).show()
            }
        }
    }
}