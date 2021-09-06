package postpc.finalproject.RoomInn.models

import android.graphics.Color
import postpc.finalproject.RoomInn.models.Point3D
import java.util.*

abstract class Furniture(
    var location: Point3D,
    var length: Float = 0f,
    var width: Float = 0f,
    var height: Float = 0f,
    var color: Int = Color.GRAY,
    var rotation: Int = 0,
    val id: String = UUID.randomUUID().toString()
) {
    var freeScale: Boolean = false

    open fun scale(scaleFactor: Float): Point3D {
        length *= scaleFactor
        width *= scaleFactor
        return Point3D(width, length, height)
    }
}