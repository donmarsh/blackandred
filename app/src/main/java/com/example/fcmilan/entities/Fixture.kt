package com.example.fcmilan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Fixture (
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "timeStamp") val timeStamp: Int,
    @ColumnInfo(name = "homeTeam") val homeTeam: String,
    @ColumnInfo(name = "awayTeam") val awayTeam: String,
    @ColumnInfo(name = "awayTeamUrl") val awayTeamUrl: String,
    @ColumnInfo(name = "homeTeamUrl") val homeTeamUrl: String,



    )