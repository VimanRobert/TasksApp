package com.example.testapp

import androidx.lifecycle.LiveData


class TaskRepos(private val daoTask: DaoTask) {
    val taskView: LiveData<List<Task>> = daoTask.getAllData()

    suspend fun  insert(task: Task){
        daoTask.insert(task)
    }
}