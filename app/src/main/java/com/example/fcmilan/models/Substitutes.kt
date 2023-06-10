package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Substitutes (

  @SerializedName("in"    ) var inNumber    : Int? = null,
  @SerializedName("out"   ) var out   : Int? = null,
  @SerializedName("bench" ) var bench : Int? = null

)