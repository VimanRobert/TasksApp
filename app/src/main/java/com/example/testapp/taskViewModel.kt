package com.example.testapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class taskViewModel(application: Application): AndroidViewModel(application) {

    private val rep: TaskRepos
    private val taskView: LiveData<List<Task>>
    init{
        val dao = TaskData.getData(application).itemDao()
        rep = TaskRepos(dao)
        taskView = rep.taskView
    }
    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO){
        rep.insert(task)
    }
}