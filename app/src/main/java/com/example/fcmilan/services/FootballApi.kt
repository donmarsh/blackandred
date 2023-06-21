package com.example.fcmilan.services

import com.example.fcmilan.BuildConfig
import com.example.fcmilan.models.fixtures.FixturesApiResponse
import com.example.fcmilan.models.players.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FootballApi {
    val API_KEY: String
        get() = BuildConfig.API_SPORTS_KEY

    @Headers("x-apisports-key: 3c3e6657cf0893388f45d6280c2b2891")
    @GET("/players")
    fun getPlayers(@Query(value = "season",encoded = true) season:String,
                   @Query(value = "team",encoded = true) teamId:String,
                   @Query(value="page",encoded = true) page:String ):Call<TeamResponse>

    @Headers("x-apisports-key: 3c3e6657cf0893388f45d6280c2b2891")
    @GET("/fixtures")
    fun getFixtures(@Query(value= "league", encoded = true) leagueId:String,
                    @Query(value= "season", encoded = true) season:String,
                    @Query(value= "team", encoded = true) teamId:String):Call<FixturesApiResponse>


}