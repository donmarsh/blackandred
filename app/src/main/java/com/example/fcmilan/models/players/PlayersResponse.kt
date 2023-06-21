package com.example.fcmilan.models.players

import com.google.gson.annotations.SerializedName


data class PlayersResponse (

  @SerializedName("player"     ) var player     : Player?               = Player(),
  @SerializedName("statistics" ) var statistics : ArrayList<Statistics> = arrayListOf()

)