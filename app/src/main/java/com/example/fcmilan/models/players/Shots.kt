package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Shots (

  @SerializedName("total" ) var total : Int? = null,
  @SerializedName("on"    ) var on    : Int? = null

)