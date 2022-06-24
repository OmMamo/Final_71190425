package com.example.final_71190425

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val btnlihatdata = findViewById<Button>(R.id.bt_lihatdata)
        val btntambahdata = findViewById<Button>(R.id.bt_tambahdata)
        val btncaridata = findViewById<Button>(R.id.bt_caridata)
        val btnlogout = findViewById<Button>(R.id.bt_logout)

        btnlihatdata.setOnClickListener {
            val i = Intent(this, LihatDataMusik::class.java);
            startActivity(i)
        }

        btntambahdata.setOnClickListener {
            val i = Intent(this, TambahDataMusik::class.java);
            startActivity(i)
        }

        btncaridata.setOnClickListener {
            val i = Intent(this, CariDataMusik::class.java);
            startActivity(i)
        }

        btnlogout.setOnClickListener {
            Toast.makeText(this,"Goodbye!", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java);
            startActivity(i)
            finish()
        }
    }
}