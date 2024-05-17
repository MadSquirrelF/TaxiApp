package com.example.taxiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taxiapp.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.uploadButton.setOnClickListener {
            val rideId = binding.uploadRideId.text.toString()
            val rideTimeStart = binding.uploadRideStart.text.toString()
            val rideTimeEnd = binding.uploadRideEnd.text.toString()
            val clientPhoneNumber = binding.uploadClientNumber.text.toString()
            val distance = binding.uploadDistance.text.toString()
            val totalSum = binding.uploadTotalSum.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Taxi History Information")

            val taxiInfoData = TaxiData(rideId, rideTimeStart, rideTimeEnd, clientPhoneNumber, distance, totalSum)

            database.child(rideId).setValue(taxiInfoData).addOnSuccessListener {
                binding.uploadRideId.text.clear()
                binding.uploadRideStart.text.clear()
                binding.uploadRideEnd.text.clear()
                binding.uploadClientNumber.text.clear()
                binding.uploadDistance.text.clear()
                binding.uploadTotalSum.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@UploadActivity, MainActivity::class.java)

                startActivity(intent)

                finish()
            }.addOnFailureListener { e ->
                // Возникла ошибка при загрузке данных
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }
}