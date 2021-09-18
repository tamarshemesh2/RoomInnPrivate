package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size

class Closet(
    position: Point3D = Point3D(),
    rotation: Point3D = Point3D(),
    scale: Point3D = Point3D(1f, 1f, 1f),
    color: Int = Color.GRAY
) : Furniture(position, rotation, scale, color) {
    init {
        unityFuncName = "addNewCloset"
        var type: String = "Closet"
    }

    override fun draw(size: Size): Path {
        val path = Path()
        val margin = 15f
        path.addRect(
            margin,
            margin,
            (scale.x * size.width) + margin/2,
            ((scale.z * size.height) / 2f) + margin,
            Path.Direction.CCW
        )
        path.moveTo(((scale.x * size.width) + margin) / 2, ((scale.z * size.height) / 2f) + margin)
        path.lineTo(((scale.x * size.width) + margin) / 2, margin)
        path.moveTo(((scale.x * size.width) + margin/2), ((scale.z * size.height) / 2f) + margin)
        path.arcTo(
            ((scale.x * size.width) + margin) / 2,
            ((scale.z * size.height) / 3f),
            (scale.x * size.width) + margin,
            ((scale.z * size.height * 2) / 3f) + margin,
            120f, 45f, false)
        path.moveTo((margin), ((scale.z * size.height) / 2f) + margin)

        path.arcTo(
            (margin) ,
            ((scale.z * size.height) / 3f),
            ((scale.x * size.width) + margin) / 2,
            ((scale.z * size.height * 2) / 3f) + margin,
            60f, -45f, false)
        return path
    }
}