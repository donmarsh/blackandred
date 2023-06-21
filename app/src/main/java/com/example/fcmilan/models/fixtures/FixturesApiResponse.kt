package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class FixturesApiResponse (

  @SerializedName("get"        ) var get        : String?             = null,
  @SerializedName("parameters" ) var parameters : Parameters?         = Parameters(),
  @SerializedName("errors"     ) var errors     : ArrayList<String>   = arrayListOf(),
  @SerializedName("results"    ) var results    : Int?                = null,
  @SerializedName("paging"     ) var paging     : Paging?             = Paging(),
  @SerializedName("response"   ) var fixturesResponse   : ArrayList<FixturesResponse> = arrayListOf()

)