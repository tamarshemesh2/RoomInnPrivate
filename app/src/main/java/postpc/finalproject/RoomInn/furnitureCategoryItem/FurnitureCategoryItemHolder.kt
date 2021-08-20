package postpc.finalproject.RoomInn.furnitureCategoryItem

import android.media.Image
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R

class FurnitureCategoryItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    val categoryTitle: TextView = view.findViewById(R.id.category_title)
    val categoryImg: View? = view.findViewById(R.id.category_img)
}