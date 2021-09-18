package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size
import postpc.finalproject.RoomInn.furnitureData.Point3D
import java.util.*

class Window(
    position: Point3D = Point3D(),
    rotation: Point3D = Point3D(),
    scale: Point3D = Point3D(80f, 60f, 10f),
    color: Int = Color.BLACK
) : Furniture(position, rotation, scale, Color.BLACK) {
    override fun draw(sizeWidth: Float, sizeHeight: Float): Path {
        val path = Path()
        val margin = 8f
        path.addRect(
            margin,
            margin,
            (scale.x * sizeWidth) + margin,
            (scale.z * sizeHeight) + margin,
            Path.Direction.CCW
        )
        return path
    }

}