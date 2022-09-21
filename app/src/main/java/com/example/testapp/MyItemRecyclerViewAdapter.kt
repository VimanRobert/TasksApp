import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.SharedBooking

class MyItemRecyclerViewAdapter(val tasks: ArrayList<SharedBooking>) :
    RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.task.text = tasks[position].title
        holder.time.text = tasks[position].time
        holder.date.text = tasks[position].date
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return tasks.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val task: TextView = itemView.findViewById(R.id.taskL)
        val time: TextView = itemView.findViewById(R.id.timeL)
        val date: TextView = itemView.findViewById(R.id.dateL)
    }
}