package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Paging (

  @SerializedName("current" ) var current : Int? = null,
  @SerializedName("total"   ) var total   : Int? = null

)