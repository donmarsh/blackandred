package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Penalty (

  @SerializedName("home" ) var home : String? = null,
  @SerializedName("away" ) var away : String? = null

)