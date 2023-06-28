package com.example.fcmilan.repositories

import com.example.fcmilan.daos.PlayerDao
import com.example.fcmilan.entities.Player
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: Flow<List<Player>> = playerDao.getAllPlayers()
    suspend fun insertAll(players: List<Player>) {
        playerDao.insertAll(players)
    }
    suspend fun deleteAll(){
        playerDao.deleteAll()
    }
}