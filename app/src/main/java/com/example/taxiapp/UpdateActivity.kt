package com.example.taxiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taxiapp.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.updateButton.setOnClickListener {
            val rideId = binding.updateRideId.text.toString()
            val rideTimeStart = binding.updateRideStart.text.toString()
            val rideTimeEnd = binding.updateRideEnd.text.toString()
            val clientPhoneNumber = binding.updateClientNumber.text.toString()
            val distance = binding.updateDistance.text.toString()
            val totalSum = binding.updateTotalSum.text.toString()

            updateData(rideId, rideTimeStart,rideTimeEnd,clientPhoneNumber,distance,totalSum)
        }
    }

    private fun updateData(rideId: String, rideTimeStart: String, rideTimeEnd: String, clientPhoneNumber: String, distance: String, totalSum: String) {

        database = FirebaseDatabase.getInstance().getReference("Taxi History Information")

        val TaxiInfoData = mapOf<String, String>(
            "rideTimeStart" to rideTimeStart,
            "rideTimeEnd" to rideTimeEnd,
            "clientPhoneNumber" to clientPhoneNumber,
            "distance" to distance,
            "totalSum" to totalSum
        )


        database.child(rideId).updateChildren(TaxiInfoData).addOnSuccessListener {
            binding.updateRideId.text.clear()
            binding.updateRideStart.text.clear()
            binding.updateRideEnd.text.clear()
            binding.updateClientNumber.text.clear()
            binding.updateDistance.text.clear()
            binding.updateTotalSum.text.clear()

            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@UpdateActivity, MainActivity::class.java)

            startActivity(intent)

            finish()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}