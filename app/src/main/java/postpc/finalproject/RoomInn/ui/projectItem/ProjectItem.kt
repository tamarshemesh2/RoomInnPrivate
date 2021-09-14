package postpc.finalproject.RoomInn.ui.projectItem

import com.google.firebase.Timestamp


data class ProjectItem(
    val projectName: String,
    val lastUpdate: Timestamp
) : Comparable<ProjectItem> {
    override fun compareTo(other: ProjectItem): Int {
        return lastUpdate.compareTo(other.lastUpdate)
    }
}