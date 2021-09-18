package postpc.finalproject.RoomInn.ui.furnitureCategoryItem

import android.content.Context
import android.graphics.Path
import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Bed
import postpc.finalproject.RoomInn.furnitureData.Furniture
import postpc.finalproject.RoomInn.furnitureData.Point3D


class FurnitureCategoryItemAdapter : RecyclerView.Adapter<FurnitureCategoryItemHolder>() {

    private val _category: MutableList<FurnitureCategoryItem> = ArrayList()
    private lateinit var projectViewModel: ProjectViewModel
    private lateinit var context: Context

    fun setViewModel(vm: ProjectViewModel) {
        projectViewModel = vm
    }

    fun setContext(cxt: Context) {
        context = cxt
    }

    fun setItems(items: Map<String, Furniture>) {
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
        val furnitureClass = furnitureCategory.furnitureClass
        holder.categoryTitle.text = furnitureCategory.furnitureCategory
        if (!holder.categoryImg.isInit()) {
            val sizeToDraw = furnitureClass.getSizeToDraw(Size(270, 250))
            holder.categoryImg.setPath(furnitureClass.draw(sizeToDraw.first,sizeToDraw.second))
        }
        holder.bg.setOnClickListener {
            projectViewModel.furniture = furnitureClass
            Navigation.findNavController(it)
                .navigate(R.id.action_addFurnitureFragment2_to_editFurnitureFragment)
        }
    }

    override fun getItemCount(): Int {
        return _category.size
    }

}