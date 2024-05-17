package com.example.taxiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taxiapp.databinding.ActivityDeleteBinding
import com.example.taxiapp.databinding.ActivitySearchBinding
import com.example.taxiapp.databinding.ActivityUpdateBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Error

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding



    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val rideId : String = binding.searchRideId.text.toString()

            if (rideId.isNotEmpty())
                readData(rideId)
            else
                Toast.makeText(this,"PLease enter the ride id",Toast.LENGTH_SHORT).show()
        }

        binding.menu.setOnClickListener {

            val intent = Intent(this@SearchActivity, MainActivity::class.java)

            startActivity(intent)

            finish()
        }



    }

    private fun readData(rideId: String) {
        database = FirebaseDatabase.getInstance().getReference("Taxi History Information")
        database.child(rideId).get().addOnSuccessListener {
            if (it.exists()){
                val start = it.child("rideTimeStart").value
                val end = it.child("rideTimeEnd").value
                val phone = it.child("clientPhoneNumber").value
                val distance = it.child("distance").value
                val total = it.child("totalSum").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchRideId.text.clear()
                binding.readRideStart.text = start.toString()
                binding.readRideEnd.text = end.toString()
                binding.readClientPhoneNumber.text = phone.toString()
                binding.readDistance.text = distance.toString()
                binding.readtotalSum.text = total.toString()
            }else{
                Toast.makeText(this,"Id does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }

}


