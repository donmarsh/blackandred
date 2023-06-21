package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Duels (

  @SerializedName("total" ) var total : Int? = null,
  @SerializedName("won"   ) var won   : Int? = null

)