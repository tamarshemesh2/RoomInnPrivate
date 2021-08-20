package postpc.finalproject.RoomInn.projectItem

import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItem
import java.sql.Timestamp // needs to be changed into firebase timestamp bellow
//import com.google.firebase.Timestamp


data class ProjectItem(
    val projectName: String,
    val lastUpdate: Timestamp
) : Comparable<FurnitureCategoryItem> {
    override fun compareTo(other: FurnitureCategoryItem): Int {
        return lastUpdate.compareTo(other.lastUpdate)
    }
}