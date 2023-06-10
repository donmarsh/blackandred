package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("player"     ) var player     : Player?               = Player(),
  @SerializedName("statistics" ) var statistics : ArrayList<Statistics> = arrayListOf()

)