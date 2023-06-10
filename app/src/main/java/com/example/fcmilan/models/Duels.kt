package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Duels (

  @SerializedName("total" ) var total : Int? = null,
  @SerializedName("won"   ) var won   : Int? = null

)