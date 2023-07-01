package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Fixture (

  @SerializedName("id"        ) var id        : Int?     = null,
  @SerializedName("referee"   ) var referee   : String?  = null,
  @SerializedName("timezone"  ) var timezone  : String?  = null,
  @SerializedName("date"      ) var date      : String?  = null,
  @SerializedName("timestamp" ) var timestamp : Long?     = null,
  @SerializedName("periods"   ) var periods   : Periods? = Periods(),
  @SerializedName("venue"     ) var venue     : Venue?   = Venue(),
  @SerializedName("status"    ) var status    : Status?  = Status()

)