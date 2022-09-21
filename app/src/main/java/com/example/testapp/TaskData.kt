package com.example.testapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskData : RoomDatabase() {
    abstract fun itemDao(): DaoTask

    companion object{
        @Volatile
        private var instance : TaskData? = null
        fun getData(context: Context): TaskData {
            return instance ?: synchronized(this) {
                val newinst = Room.databaseBuilder(
                    context.applicationContext,
                    TaskData::class.java,
                    "Tasks_List"
                ).fallbackToDestructiveMigration().build()
                instance = newinst
                return newinst
            }
        }
    }
}