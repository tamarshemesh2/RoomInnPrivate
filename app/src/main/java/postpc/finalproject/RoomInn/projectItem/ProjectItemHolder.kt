package postpc.finalproject.RoomInn.projectItem

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R

class ProjectItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectName: TextView = view.findViewById(R.id.project_name)
}