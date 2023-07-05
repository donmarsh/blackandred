package com.example.fcmilan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ApiCall (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "last_call") val lastCall: Long,
    @ColumnInfo(name="api_call") val apiCall: String

    )
