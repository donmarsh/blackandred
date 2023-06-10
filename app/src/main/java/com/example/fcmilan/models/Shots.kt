package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Shots (

  @SerializedName("total" ) var total : Int? = null,
  @SerializedName("on"    ) var on    : Int? = null

)