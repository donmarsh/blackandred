package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Tackles (

  @SerializedName("total"         ) var total         : Int?    = null,
  @SerializedName("blocks"        ) var blocks        : String? = null,
  @SerializedName("interceptions" ) var interceptions : Int?    = null

)