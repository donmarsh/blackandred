package com.example.fcmilan.models.fixtures

import com.google.gson.annotations.SerializedName


data class Response (

    @SerializedName("fixture" ) var fixture : Fixture? = Fixture(),
    @SerializedName("league"  ) var league  : League?  = League(),
    @SerializedName("teams"   ) var teams   : Teams?   = Teams(),
    @SerializedName("goals"   ) var goals   : Goals?   = Goals(),
    @SerializedName("score"   ) var score   : Score?   = Score()

)