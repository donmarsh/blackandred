package com.example.fcmilan.models

import com.google.gson.annotations.SerializedName


data class Passes (

  @SerializedName("total"    ) var total    : Int? = null,
  @SerializedName("key"      ) var key      : Int? = null,
  @SerializedName("accuracy" ) var accuracy : Int? = null

)