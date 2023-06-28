package com.example.fcmilan.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Player(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name="imageUrl") val imageUrl:String,
    @ColumnInfo(name="height") val height:String,
    @ColumnInfo(name="age") val age:Int,


    )