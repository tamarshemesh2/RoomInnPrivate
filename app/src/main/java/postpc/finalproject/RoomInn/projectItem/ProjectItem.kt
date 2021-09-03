package postpc.finalproject.RoomInn.projectItem

import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItem
import com.google.firebase.Timestamp


data class ProjectItem(
    val projectName: String,
    val lastUpdate: Timestamp
) : Comparable<ProjectItem> {
    override fun compareTo(other: ProjectItem): Int {
        return lastUpdate.compareTo(other.lastUpdate)
    }
}