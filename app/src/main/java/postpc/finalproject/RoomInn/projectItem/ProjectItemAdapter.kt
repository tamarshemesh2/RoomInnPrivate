package postpc.finalproject.RoomInn.projectItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItem
import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItemHolder
import java.sql.Timestamp


class ProjectItemAdapter : RecyclerView.Adapter<FurnitureCategoryItemHolder>() {

    private val _projects: MutableList<FurnitureCategoryItem> = ArrayList()

    fun setItems(items: Map<String, Timestamp>) {
        _projects.clear()
        items.forEach { _projects.add(FurnitureCategoryItem(it.key, it.value)) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureCategoryItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_project, parent, false)
        return FurnitureCategoryItemHolder(view)
    }

    override fun onBindViewHolder(holder: FurnitureCategoryItemHolder, position: Int) {
        val projectItem = _projects[position]
//        holder.projectName.text = projectItem.projectName

    }

    override fun getItemCount(): Int {
        return _projects.size
    }

}