package com.example.testapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.testapp.DataList.Companion.TABLE_TASKS
import com.example.testapp.databinding.FragmentFirstBinding
import com.j256.ormlite.dao.Dao
import java.text.SimpleDateFormat
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils
import java.util.*


class FirstFragment : Fragment() {
    private  var tasks: ArrayList<Parcelable>? = arrayListOf()
    private lateinit var binding: FragmentFirstBinding
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var task: Task
    private lateinit var connectionSource: ConnectionSource


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toCalendar.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.toList2?.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("key",tasks)
            findNavController().navigate(R.id.action_FirstFragment_to_fragmentList,bundle)
        }
        val calendar = Calendar.getInstance()
        val timeView = view.findViewById<TextView>(R.id.timeView)

        binding.writeTime.setOnClickListener {
            val calendarT = Calendar.getInstance()
            //val hour = calendarT.get(Calendar.HOUR)
            //val minute = calendarT.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog.OnTimeSetListener{
                timepick, hour, minute ->
                calendarT.set(Calendar.HOUR, hour)
                calendarT.set(Calendar.MINUTE, minute)
                timeView.text = SimpleDateFormat("HH:mm").format(calendarT.time)
            }
            TimePickerDialog(context, timePickerDialog, calendarT.get(Calendar.HOUR_OF_DAY), calendarT.get(Calendar.MINUTE), true).show()
        }

        binding.writeDate.setOnClickListener() {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { view, year, monthOfYear, dayOfMonth ->
                        val dat = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                        binding.writeDate.setText(dat)
                    },
                    year,
                    month,
                    day
                )
            }
            datePickerDialog?.show()
        }
        val newTitle = view.findViewById<EditText>(R.id.writeTask)
        //val timeView = view.findViewById<TextView>(R.id.timeView)
        val newDate = view.findViewById<EditText>(R.id.writeDate)


        binding.addTask.setOnClickListener {
            if(newTitle.text.isEmpty()){
                newTitle.setError("Field required !")
                Toast.makeText(context, "Failed to add the task !", Toast.LENGTH_SHORT).show()
            }
            if(newDate.text.isEmpty()){
                newDate.setError("Field required !")
                Toast.makeText(context, "Failed to add the task !", Toast.LENGTH_SHORT).show()
            }
            else {

                val theTask = newTitle.text.toString()
                val theTime = timeView.text.toString()
                val theDate = newDate.text.toString()
                val sharedBooking = SharedBooking(theTask, theTime, theDate)
                tasks?.add(sharedBooking)


/*              USING SQLITE


                val taskl = view.findViewById<TextView>(R.id.taskL)
                val timel = view.findViewById<TextView>(R.id.timeL)
                val datel = view.findViewById<TextView>(R.id.dateL)

                val dataList = context?.let { it1 -> DataList(it1 , factory = null)}
                tasks?.add(sharedBooking)
                Toast.makeText(context, "Task added. Check the list", Toast.LENGTH_SHORT).show()
                dataList?.addVal(theTask, theTime, theDate)
                newTitle.text.clear()
                newDate.text.clear()
                timeView.text = "00 : 00"

 */
                /*
                val cursor = dataList?.getVal()
                cursor!!.moveToFirst()
                taskl.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TASK))+" ; ")
                timel.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TIME))+" ; ")
                datel.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_DATE))+".")
                while(cursor.moveToNext()){
                    taskl.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TASK))+" ; ")
                    timel.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_TIME))+" ; ")
                    datel.append(cursor.getString(cursor.getColumnIndex(DataList.COLUMN_DATE))+".")
                }
                cursor.close()
/*

                 */
                editor = getSharedPreferences(sharedBooking, MODE_PRIVATE).edit()
                editor.putString("task", "$theTask")
                editor.putString("time", "$theTime")
                editor.putString("date", "$theDate")
                editor.apply()
*/
            }
        }


        /*        USING JSON

        val Settings = JSONObject()

        Settings.put("Task", binding.writeTask.text)
        Settings.put("Time", binding.writeTime.text)
        Settings.put("Date", binding.writeDate.text)

        val file = File("/data/data/com.example.myapplication/files/List_Of_Tasks.json")

        if(!file.exists()){
            file.createNewFile()
        }
        else if(file.exists()){
            file.writeText(Settings.toString())
        }
*/

    }
}