package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size


class Bed(
    position: Point3D = Point3D(),
    rotation: Point3D = Point3D(),
    scale: Point3D = Point3D(140f, 40f, 190f),
    color: Int = Color.GRAY
) : Furniture(position, rotation, scale, color) {
    init {
        unityFuncName = "addNewBed"
    }

    override fun draw(sizeWidth:Float, sizeHeight:Float): Path {
        val path = Path()
        val margin = 8f
        path.addRoundRect(
            margin,
            margin,
            (scale.x * sizeWidth) + margin,
            (scale.z * sizeHeight) + margin,
            (scale.x * sizeWidth).toFloat() / 15,
            (scale.z * sizeHeight).toFloat() / 15,
            Path.Direction.CCW
        )
        path.addRoundRect(
            margin,
            margin,
            ((scale.x * sizeWidth / 2f) + margin),
            (scale.z * sizeHeight + margin) / 2.5f,
            (scale.x * sizeWidth)/ 5,
            (scale.z * sizeHeight) / 5,
            Path.Direction.CCW
        )
        path.addRoundRect(
            ((scale.x * sizeHeight) / 2) + margin,
            margin,
            ((scale.x * sizeWidth) + margin),
            (scale.z * sizeHeight + margin) / 2.5f,
            (scale.x * sizeWidth) / 5,
            (scale.z * sizeHeight) / 5,
            Path.Direction.CCW
        )
        path.moveTo(margin,(scale.z * sizeHeight + margin) / 2f)
        path.lineTo(((scale.x * sizeWidth) + margin),(scale.z * sizeHeight + margin) / 2f)
        return path
    }
}

