package com.example.testapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks_List")
data class Task(
    @PrimaryKey(autoGenerate = true)
    //val id: Int,
    val title:String ="",
    val time:String = "",
    val date:String=""
)