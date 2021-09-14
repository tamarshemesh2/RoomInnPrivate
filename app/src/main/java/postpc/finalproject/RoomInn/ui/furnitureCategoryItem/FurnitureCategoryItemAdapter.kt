package postpc.finalproject.RoomInn.ui.furnitureCategoryItem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Bed
import postpc.finalproject.RoomInn.furnitureData.Point3D


class FurnitureCategoryItemAdapter : RecyclerView.Adapter<FurnitureCategoryItemHolder>() {

    private val _category: MutableList<FurnitureCategoryItem> = ArrayList()
    private lateinit var projectViewModel: ProjectViewModel

    fun setViewModel(vm: ProjectViewModel) {
        projectViewModel = vm
    }

    fun setItems(items: Map<String, Int>) {
        _category.clear()
        items.forEach { _category.add(FurnitureCategoryItem(it.key, it.value)) }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FurnitureCategoryItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_category_furniture, parent, false)
        return FurnitureCategoryItemHolder(view)
    }

    override fun onBindViewHolder(holder: FurnitureCategoryItemHolder, position: Int) {
        val furnitureCategory = _category[position]
        holder.categoryTitle.text = furnitureCategory.furnitureCategory
        holder.categoryImg.setImageResource(furnitureCategory.imageID);
        holder.categoryImg.setOnClickListener {
            projectViewModel.furniture = Bed(Point3D(projectViewModel.currentX, projectViewModel.currentY,0f))
            Navigation.findNavController(it)
                .navigate(R.id.action_addFurnitureFragment2_to_editFurnitureFragment)
        }
    }

    override fun getItemCount(): Int {
        return _category.size
    }

}