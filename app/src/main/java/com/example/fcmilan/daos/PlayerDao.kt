package com.example.fcmilan.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fcmilan.entities.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player:Player)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players:List<Player>)
    @Query("DELETE FROM Player")
    suspend fun deleteAll()
    @Query("SELECT * FROM player ORDER BY id DESC")
    fun getAllPlayers(): Flow<List<Player>>


}