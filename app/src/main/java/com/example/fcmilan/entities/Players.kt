package com.example.fcmilan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Players(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "player_number") val number: Int,
    @ColumnInfo(name="imageUrl") val imageUrl:String,
    @ColumnInfo(name="age") val age:Int,


    )