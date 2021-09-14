package postpc.finalproject.RoomInn.ui.furnitureCategoryItem

//import com.google.firebase.Timestamp


data class FurnitureCategoryItem(
        val furnitureCategory: String,
        val imageID: Int
) : Comparable<FurnitureCategoryItem> {
    override fun compareTo(other: FurnitureCategoryItem): Int {
        return furnitureCategory.compareTo(other.furnitureCategory)
    }
}