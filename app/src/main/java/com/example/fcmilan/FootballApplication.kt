package com.example.fcmilan

import android.app.Application
import com.example.fcmilan.database.FootballDatabase
import com.example.fcmilan.repositories.FixtureRepository
import com.example.fcmilan.repositories.PlayerRepository

class FootballApplication:Application() {
    val database by lazy { FootballDatabase.getDatabase(this) }
    val playerRepository by lazy { PlayerRepository(database.playerDao()) }
    val fixtureRepository by lazy { FixtureRepository(database.fixtureDao()) }


}