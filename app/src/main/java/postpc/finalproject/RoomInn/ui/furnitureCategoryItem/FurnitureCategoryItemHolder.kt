package postpc.finalproject.RoomInn.ui.furnitureCategoryItem

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R

class FurnitureCategoryItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val categoryTitle: TextView = view.findViewById(R.id.category_title)
    val categoryImg: ImageView = view.findViewById(R.id.category_img)
}