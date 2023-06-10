package com.example.fcmilan.models.fixtures

import com.example.fcmilan.models.fixtures.Away
import com.example.fcmilan.models.fixtures.Home
import com.google.gson.annotations.SerializedName


data class Teams (

  @SerializedName("home" ) var home : Home? = Home(),
  @SerializedName("away" ) var away : Away? = Away()

)