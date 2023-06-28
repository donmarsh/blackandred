package com.example.fcmilan.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fcmilan.entities.Fixture
import kotlinx.coroutines.flow.Flow

interface FixtureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fixture: Fixture)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fixtures:List<Fixture>)
    @Query("DELETE FROM Fixture")
    suspend fun deleteAll()
    @Query("SELECT * FROM fixture ORDER BY id DESC")
    fun getAllFixtures(): Flow<List<Fixture>>
}