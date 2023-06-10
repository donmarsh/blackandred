package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Extratime (

  @SerializedName("home" ) var home : String? = null,
  @SerializedName("away" ) var away : String? = null

)