package com.example.address

import java.io.Serializable

data class CleanAddressResponse(
    val source: String,
    val result: String,
    val qc: Int,
    val qc_complete: Int,
    val qc_house: Int,
    val qc_geo: Int,
    val qc_street: Int,
    val qc_exact: Int,
    val qc_conflict: Int,
    val qc_post: Int,
    val house: String,
    val block: String,
    val flat: String,
    val flat_area: String,
    val postal_code: String,
    val country: String,
    val region: String,
    val area: String,
    val city: String,
    val city_district: String,
    val settlement: String,
    val street: String,
    val geo_lat: String,
    val geo_lon: String
) : Serializable

