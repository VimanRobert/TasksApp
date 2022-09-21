package com.example.testapp

import MyItemRecyclerViewAdapter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.database.*
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.widget.Button
import android.widget.TextView
import androidx.datastore.core.DataStore
import com.example.testapp.DataList
//import com.example.testapp.databinding.FragmentListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.prefs.Preferences

class FragmentList : Fragment() {

    private lateinit var tasks: ArrayList<SharedBooking>
    //private lateinit var dataList: DataList
    //private lateinit var binding: FragmentListBinding
    private lateinit var myItemRecyclerViewAdapter: MyItemRecyclerViewAdapter
    //private lateinit var storedData: DataStore<Preferences>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.tasksList)
        tasks = arguments?.getParcelableArrayList<SharedBooking>("key") as ArrayList<SharedBooking>

        val adapter = MyItemRecyclerViewAdapter(tasks)
        recyclerView.adapter = adapter
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val del = view.findViewById<Button>(R.id.deleteTask)
        val task = view.findViewById<TextView>(R.id.taskL)
        val time = view.findViewById<TextView>(R.id.timeL)
        val date = view.findViewById<TextView>(R.id.dateL)
        val dataList = context?.let { it1 -> DataList(it1 , factory = null)}
        val query = dataList?.getVal(task.text.toString())
        if(query!=null){
            task.text = query.title.toString()
            time.text = query.time.toString()
            date.text = query.date.toString()
        }else{
            "The list is empty".also { task.text = it }
        }


        /*
        task.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TASK))+" ; ")
        time.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TIME))+" ; ")
        date.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_DATE))+".")
        while(cursor.moveToNext()){
            task.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TASK))+" ; ")
            time.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TIME))+" ; ")
            date.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_DATE))+".")
        }
         */
        //cursor.close()

/*
        val pref = this.requireActivity().getSharedPreferences("listPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val jsonPref = pref.getString("ListOfTasks", null)
        val type: Type = object : TypeToken<ArrayList<SharedBooking?>?>(){}.type
        tasks = gson.fromJson<Any>(jsonPref, type) as ArrayList<SharedBooking>
        if(tasks.isEmpty()){

        }
        myItemRecyclerViewAdapter =MyItemRecyclerViewAdapter(tasks)


        val deleteBtn = view.findViewById<Button>(R.id.deleteTask)
        deleteBtn.setOnClickListener(){


        }
 */
    }



    /*
    fun removeProduct(view: View) {
        val dbHandler = DataList(this, null, null, 1)

        val result = dbHandler.deleteProduct(
            TASK.text.toString())

        if (result) {
            ID.text = "Record Deleted"
            TASK.setText("")
            TIME.setText("")
            DATE.setText("")
        } else
            ID.text = "No Match Found"
    }
     */
}