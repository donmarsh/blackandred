package com.example.fcmilan.daos

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.fcmilan.entities.ApiCall

interface ApiCallDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apiCall: ApiCall)

}