package postpc.finalproject.RoomInn.furnitureCategoryItem

import android.graphics.drawable.Drawable
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R


class FurnitureCategoryItemAdapter : RecyclerView.Adapter<FurnitureCategoryItemHolder>() {

    private val _category: MutableList<FurnitureCategoryItem> = ArrayList()

    fun setItems(items: Map<String, Drawable>) {
        _category.clear()
        items.forEach { _category.add(FurnitureCategoryItem(it.key,it.value)) }
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
        holder.categoryImg.setImageDrawable(furnitureCategory.image)
    }

    override fun getItemCount(): Int {
        return _category.size
    }

}