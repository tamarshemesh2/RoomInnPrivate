package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size


class Bed(
        position: Point3D = Point3D(),
        rotation: Point3D = Point3D(),
        scale: Point3D = Point3D(1f, 1f, 1f),
        color: Int = Color.GRAY,
) : Furniture(position, rotation, scale, color) {
    init {
        unityFuncName = "addNewBed"
        var type: String = "Bed"
    }

    override fun draw(size: Size): Path {
        val path = Path()
        val margin = 15f
        path.addRoundRect(
            margin,
            margin,
            (scale.x * size.width) + margin,
            (scale.z * size.height) + margin,
            size.width.toFloat() / 15,
            size.height.toFloat() / 15,
            Path.Direction.CCW
        )
        path.addRoundRect(
            margin,
            margin,
            ((scale.x * size.width / 2f) + margin),
            (scale.z * size.height + margin) / 2.5f,
            size.width.toFloat() / 5,
            size.height.toFloat() / 5,
            Path.Direction.CCW
        )
        path.addRoundRect(
            ((scale.x * size.height) / 2) + margin,
            margin,
            ((scale.x * size.width) + margin),
            (scale.z * size.height + margin) / 2.5f,
            size.width.toFloat() / 5,
            size.height.toFloat() / 5,
            Path.Direction.CCW
        )
        path.moveTo(margin,(scale.z * size.height + margin) / 2f)
        path.lineTo(((scale.x * size.width) + margin),(scale.z * size.height + margin) / 2f)
        return path
    }
}

