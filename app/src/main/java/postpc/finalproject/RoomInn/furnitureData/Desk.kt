package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size

class Desk(position : Point3D = Point3D(),
           rotation : Point3D = Point3D(),
           scale : Point3D = Point3D(1f,1f,1f),
           color : Int = Color.GRAY): Furniture(position,rotation, scale, color){
    init {
        unityFuncName = "addNewDesk"
        var type: String = "Desk"
    }

    override fun draw(size: Size): Path {
        val path = Path()
        val margin = 15f
        path.addRect(
            margin,
            margin,
            (scale.x * size.width) + margin,
            (scale.z * size.height)+margin,
            Path.Direction.CCW
        )
        return path
    }
}