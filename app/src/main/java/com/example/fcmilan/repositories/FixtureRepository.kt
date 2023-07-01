package com.example.fcmilan.repositories

import com.example.fcmilan.daos.FixtureDao
import com.example.fcmilan.entities.Fixture
import kotlinx.coroutines.flow.Flow

class FixtureRepository(private val fixtureDao: FixtureDao) {
    val allFixtures: Flow<List<Fixture>> = fixtureDao.getAllFixtures()
    suspend fun insertAll(fixtures: List<Fixture>) {
        fixtureDao.insertAll(fixtures)
    }
    suspend fun deleteAll(){
        fixtureDao.deleteAll()
    }
}


