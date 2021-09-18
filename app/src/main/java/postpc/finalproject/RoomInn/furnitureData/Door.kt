package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size
import java.util.*

class Door(
    position: Point3D = Point3D(),
    rotation: Point3D = Point3D(),
    scale: Point3D = Point3D(80f, 206f, 80f),
    color: Int = Color.BLACK
) : Furniture(position, rotation, scale, Color.BLACK) {
    init{
        type = "Door"
    }
    override fun draw(sizeWidth: Float, sizeHeight: Float): Path {
        val path = Path()
        val margin = 8f

        path.moveTo(margin, ((scale.z * sizeHeight) + margin))
        path.arcTo(
            -scale.x * sizeWidth + margin,
            margin,
            scale.x * sizeWidth + margin,
            (scale.z * sizeHeight * 2) + margin,
            -90f, 90f, false
        )
        path.lineTo(margin * 0.5f, ((scale.z * sizeHeight) + margin))
        path.moveTo(margin * 2, ((scale.z * sizeHeight) + margin))
        path.lineTo(margin * 2, margin)
        return path
    }

}