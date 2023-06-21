package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class Goals (

  @SerializedName("total"    ) var total    : Int?    = null,
  @SerializedName("conceded" ) var conceded : Int?    = null,
  @SerializedName("assists"  ) var assists  : Int?    = null,
  @SerializedName("saves"    ) var saves    : String? = null

)