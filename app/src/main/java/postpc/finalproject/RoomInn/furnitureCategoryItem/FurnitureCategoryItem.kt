package postpc.finalproject.RoomInn.furnitureCategoryItem

import android.media.Image
import java.sql.Timestamp // needs to be changed into firebase timestamp bellow
//import com.google.firebase.Timestamp


data class FurnitureCategoryItem(
    val furnitureCategory: String,
    val image: Image
) : Comparable<FurnitureCategoryItem> {
    override fun compareTo(other: FurnitureCategoryItem): Int {
        return furnitureCategory.compareTo(other.furnitureCategory)
    }
}