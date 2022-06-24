package com.example.final_71190425

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class LihatDataMusik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihatdatamusik)

        val firestore = FirebaseFirestore.getInstance()

        val btnMenu = findViewById<Button>(R.id.bt_menu)

        btnMenu.setOnClickListener {
            val i = Intent(this, SecondActivity::class.java)
            startActivity(i)
        }

        firestore.collection("BankLagu")
            .get()
            .addOnSuccessListener { hasilPencarian->

                val listMusik = ArrayList<Musik>()

                for (document in hasilPencarian){
                    listMusik.add(Musik("${document.data["Judul Lagu"]}","${document.data["Nama Penyanyi"]}", "${document.data["Judul Album"]}", "${document.data["Genre"]}", "${document.data["Tahun Rilis"]}"))
                }

                //siapkan RecyclerView
                val rvMusik = findViewById<RecyclerView>(R.id.rvListMusik)
                rvMusik.layoutManager = LinearLayoutManager (this)
                val adapter = MusikAdapter(listMusik)
                rvMusik.adapter = adapter
            }
            .addOnFailureListener{
                Log.d("Gagal", "Data Tidak Ada")
            }

    }
}