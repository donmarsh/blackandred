package com.example.fcmilan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fcmilan.daos.FixtureDao
import com.example.fcmilan.daos.PlayerDao
import com.example.fcmilan.entities.Fixture
import com.example.fcmilan.entities.Player

@Database(entities = arrayOf(Player::class,Fixture::class), version = 1, exportSchema = false)
public abstract class FootballDatabase:RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun fixtureDao(): FixtureDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FootballDatabase? = null

        fun getDatabase(context: Context): FootballDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootballDatabase::class.java,
                    "football_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}