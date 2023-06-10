package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Birth (

  @SerializedName("date"    ) var date    : String? = null,
  @SerializedName("place"   ) var place   : String? = null,
  @SerializedName("country" ) var country : String? = null

)