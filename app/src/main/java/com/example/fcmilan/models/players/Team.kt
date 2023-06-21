package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Team (

  @SerializedName("id"   ) var id   : Int?    = null,
  @SerializedName("name" ) var name : String? = null,
  @SerializedName("logo" ) var logo : String? = null

)