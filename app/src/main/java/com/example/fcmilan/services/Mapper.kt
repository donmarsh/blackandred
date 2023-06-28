package com.example.fcmilan.services

import com.example.fcmilan.entities.Fixture
import com.example.fcmilan.models.fixtures.FixturesResponse

fun com.example.fcmilan.models.players.Player.toPlayerEntity() = com.example.fcmilan.entities.Player(
    id = null,
    name = "$name",
    imageUrl = "$photo",
    age = age!!,
    height = "$height")

fun FixturesResponse.toFixture() = Fixture(
    id = null,
    timeStamp =fixture!!.timestamp!!,
    homeTeam = "${teams!!.home!!.name}",
    awayTeam = "${teams!!.away!!.name}",
    homeTeamUrl = "${teams!!.home!!.logo}",
    awayTeamUrl = "${teams!!.away!!.logo}"

)