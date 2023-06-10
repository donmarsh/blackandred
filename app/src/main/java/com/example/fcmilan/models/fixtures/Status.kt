package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Status (

  @SerializedName("long"    ) var long    : String? = null,
  @SerializedName("short"   ) var short   : String? = null,
  @SerializedName("elapsed" ) var elapsed : Int?    = null

)