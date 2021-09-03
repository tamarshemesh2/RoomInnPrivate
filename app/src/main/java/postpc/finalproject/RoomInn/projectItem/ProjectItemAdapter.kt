package postpc.finalproject.RoomInn.projectItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import postpc.finalproject.RoomInn.R


class ProjectItemAdapter : RecyclerView.Adapter<ProjectItemHolder>() {

    private val _projects: MutableList<ProjectItem> = ArrayList()

    fun setItems(items: Map<String, Timestamp>) {
        _projects.clear()
        items.forEach { _projects.add(ProjectItem(it.key, it.value)) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectItemHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectItemHolder, position: Int) {
        val projectItem = _projects[position]
        holder.projectName.text = projectItem.projectName

    }

    override fun getItemCount(): Int {
        return _projects.size
    }

}