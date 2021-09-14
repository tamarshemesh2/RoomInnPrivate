package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size

import java.util.*
abstract class Furniture(
    var position : Point3D,
    var rotation : Point3D,
    var scale : Point3D,
    var color: Int = Color.GRAY,
    val id: String = UUID.randomUUID().toString()) {

    var unityFuncName: String = ""
    var freeScale: Boolean = false

    open fun scale(scaleFactor: Float): Point3D {
        return Point3D(scale.multiply(scaleFactor))
    }
    abstract fun draw(size: Size): Path
}
