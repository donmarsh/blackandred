package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Cards (

  @SerializedName("yellow"    ) var yellow    : Int? = null,
  @SerializedName("yellowred" ) var yellowred : Int? = null,
  @SerializedName("red"       ) var red       : Int? = null

)