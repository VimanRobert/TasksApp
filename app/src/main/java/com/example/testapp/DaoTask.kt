package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoTask {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("SELECT * FROM Tasks_List")
    fun getAllData():LiveData<List<Task>>

    @Delete
    suspend fun delete(task: Task)
}