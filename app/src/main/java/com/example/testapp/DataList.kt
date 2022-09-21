package com.example.testapp

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues


//  DATA CLASS FOR SQLITE !!!!



class DataList(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME,
                        factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_TASKS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TASK
                + " TEXT," + COLUMN_TIME + " TIME," + COLUMN_DATE + "DATE"
                + ")")
        db?.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,
                           newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS)
        onCreate(db)
    }

    //   INSERT VALUES

    fun addVal(newTask: String, newTime: String, newDate: String){
        val values = ContentValues()
        values.put(COLUMN_TIME, newTask)
        values.put(COLUMN_TIME, newTime)
        values.put(COLUMN_DATE, newDate)

        val dataBase = this.writableDatabase
        dataBase.insert(TABLE_TASKS, null, values)
        dataBase.close()
    }
/*
    fun addVal(task: Task){
        val values = ContentValues()
    values.put(COLUMN_TASK, task.title)
    values.put(COLUMN_TIME, task.time)
    values.put(COLUMN_DATE, task.date)
    val dataBase = this.writableDatabase
    dataBase.insert(TABLE_TASKS, null, values)
    dataBase.close()
    }

 */
         //   SHOW VALUES
//Cursor
    fun getVal(taskDesc: String): Task? {
        val dataBase = this.readableDatabase

        //return dataBase.rawQuery("SELECT * FROM "+ TABLE_TASKS, null)
    val query = "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_TASK = \"$taskDesc\""
    val cursor = dataBase.rawQuery(query, null)
    var task: Task? = null
    if(cursor.moveToFirst()){
        cursor.moveToFirst()
    val qTask = cursor.getString(1)
    val qTime = cursor.getString(1)
    val qDate = cursor.getString(1)
    task = Task(qTask, qTime, qDate)
    cursor.close()
    }
    dataBase.close()
    return task
    }

    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "TasksList.db"
        val TABLE_TASKS = "TASKS"

        val COLUMN_ID = "ID"
        val COLUMN_TASK = "TASK"
        val COLUMN_TIME = "TIME"
        val COLUMN_DATE = "DATE"
    }

    //   DELETE VALUES

    fun deleteProduct(taskDesc: String): Boolean {

        var result = false

        val query =
            "SELECT * FROM $TABLE_TASKS WHERE $COLUMN_TASK = \"$taskDesc\""

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_TASKS, COLUMN_ID + " = ?",
                arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

}