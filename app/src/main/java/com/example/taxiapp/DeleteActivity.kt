package com.example.taxiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.taxiapp.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val rideId = binding.deleteRideId.text.toString()

            if (rideId.isNotEmpty())
                deleteData(rideId)
            else
                Toast.makeText(this, "Please enter ride id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteData(rideId: String){
        database = FirebaseDatabase.getInstance().getReference("Taxi History Information")
        database.child(rideId).removeValue().addOnSuccessListener {
            binding.deleteRideId.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@DeleteActivity, MainActivity::class.java)

            startActivity(intent)

            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}