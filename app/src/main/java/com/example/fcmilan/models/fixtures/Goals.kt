package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("home" ) var home : Int? = null,
  @SerializedName("away" ) var away : Int? = null

)