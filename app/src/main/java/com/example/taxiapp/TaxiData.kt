package com.example.taxiapp

data class TaxiData(
    var rideId: String? = null,
    val rideTimeStart: String? = null,
    val rideTimeEnd: String? = null,
    val clientPhoneNumber: String? = null,
    val distance: String? = null,
    val totalSum: String? = null,
) {}
