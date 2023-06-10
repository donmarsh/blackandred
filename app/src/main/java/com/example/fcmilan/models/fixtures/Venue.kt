package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Venue (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("city" ) var city : String? = null

)