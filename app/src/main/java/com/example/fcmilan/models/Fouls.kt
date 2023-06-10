package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Fouls (

  @SerializedName("drawn"     ) var drawn     : Int? = null,
  @SerializedName("committed" ) var committed : Int? = null

)