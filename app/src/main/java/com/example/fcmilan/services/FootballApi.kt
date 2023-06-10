package com.example.fcmilan.services

import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("/players")
    fun getPlayers(@Query(value = "season",encoded = true) season:String,
                   @Query(value = "team",encoded = true) teamId:String,
                   @Query(value="page",encoded = true) page:String ) {
    }
    @GET("/fixtures")
    fun getFixtures(@Query(value= "league", encoded = true) leagueId:String,
                    @Query(value= "season", encoded = true) season:String,
                    @Query(value= "team", encoded = true) teamId:String){

    }
}