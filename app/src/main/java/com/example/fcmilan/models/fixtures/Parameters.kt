package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Parameters (

  @SerializedName("team"   ) var team   : String? = null,
  @SerializedName("season" ) var season : String? = null,
  @SerializedName("league" ) var league : String? = null

)