package com.example.final_71190425

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MusikAdapter (val listMusik: ArrayList<Musik>): RecyclerView.Adapter<MusikAdapter.MusikHolder>() {
    class MusikHolder(val v: View): RecyclerView.ViewHolder(v){
        var musik: Musik? = null
        val firestore = FirebaseFirestore.getInstance()

        fun bindView(musik: Musik){
            this.musik = musik
            v.findViewById<TextView>(R.id.tv_judulLagu).text = musik.JudulLagu
            v.findViewById<TextView>(R.id.tv_nama).text = musik.NamaPenyanyi
            v.findViewById<TextView>(R.id.tv_judulAlbum).text = musik.JudulAlbum
            v.findViewById<TextView>(R.id.tv_genre).text = musik.Genre
            v.findViewById<TextView>(R.id.tv_tahunRilis).text = musik.TahunRilis.toString()


            //button delete
            v.findViewById<Button>(R.id.bt_delete).setOnClickListener{

                firestore.collection("BankLagu")
                    .whereEqualTo("JudulLagu", musik.JudulLagu)
                    .get()
                    .addOnSuccessListener {
                        for (document in it){
                            firestore.collection("BankLagu").document(document.id).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(v.context,"Data Berhasil dihapus",Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener{
                        Toast.makeText(v.context,"Data Gagal dihapus",Toast.LENGTH_SHORT).show()
                    }
                (v.context as Activity).recreate()
            }

            //button edit
            v.findViewById<Button>(R.id.bt_edit).setOnClickListener{

                val i = Intent(v.context, EditDataMusik::class.java)
                i.putExtra("JudulLagu",musik.JudulLagu)
                i.putExtra("NamaPenyanyi",musik.NamaPenyanyi)
                i.putExtra("JudulAlbum",musik.JudulAlbum)
                i.putExtra("Genre",musik.Genre)
                i.putExtra("TahunRilis", musik.TahunRilis)
                v.context.startActivity(i)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusikAdapter.MusikHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_musik, parent, false)
        return MusikHolder(v)
    }
    //memilih file layout XML yang akan dijadikan container

    override fun onBindViewHolder(holder: MusikAdapter.MusikHolder, position: Int) {
        //memasang data ke dalam file layout XML yang telah dipilih
        holder.bindView(listMusik[position])
    }

    override fun getItemCount(): Int {
        //mengembalikan jumlah item yang terdapat pada RecyclerView
        return listMusik.size
    }
}