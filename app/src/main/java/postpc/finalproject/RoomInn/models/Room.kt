package postpc.finalproject.RoomInn

import android.graphics.drawable.VectorDrawable
import postpc.finalproject.RoomInn.models.*

data class Room(
    var Corners: MutableList<Point3D> = mutableListOf(),
    var furniture: Map<String, Furniture> = mutableMapOf(),
    var width: Int = 0,
    var windows: MutableList<Window> = mutableListOf(),
    var doors: MutableList<Door> = mutableListOf(),
    val displayRatio: Float = 1f,
) {
}