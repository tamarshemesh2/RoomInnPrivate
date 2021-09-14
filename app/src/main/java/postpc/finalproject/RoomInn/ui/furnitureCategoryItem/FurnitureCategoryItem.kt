package postpc.finalproject.RoomInn.ui.furnitureCategoryItem

import android.graphics.Path

//import com.google.firebase.Timestamp


data class FurnitureCategoryItem(
    val furnitureCategory: String,
    val imageID: Path
) : Comparable<FurnitureCategoryItem> {
    override fun compareTo(other: FurnitureCategoryItem): Int {
        return furnitureCategory.compareTo(other.furnitureCategory)
    }
}