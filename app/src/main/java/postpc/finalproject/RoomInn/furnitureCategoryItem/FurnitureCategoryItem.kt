package postpc.finalproject.RoomInn.furnitureCategoryItem

import android.graphics.drawable.Drawable
import java.sql.Timestamp // needs to be changed into firebase timestamp bellow
//import com.google.firebase.Timestamp


data class FurnitureCategoryItem(
        val furnitureCategory: String,
        val image: Drawable
) : Comparable<FurnitureCategoryItem> {
    override fun compareTo(other: FurnitureCategoryItem): Int {
        return furnitureCategory.compareTo(other.furnitureCategory)
    }
}