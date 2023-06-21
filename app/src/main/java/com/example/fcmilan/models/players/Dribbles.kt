package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Dribbles (

  @SerializedName("attempts" ) var attempts : Int?    = null,
  @SerializedName("success"  ) var success  : Int?    = null,
  @SerializedName("past"     ) var past     : String? = null

)