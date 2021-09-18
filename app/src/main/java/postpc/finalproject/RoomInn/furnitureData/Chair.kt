package postpc.finalproject.RoomInn.furnitureData

import android.graphics.Color
import android.graphics.Path
import android.util.Size

class Chair(position : Point3D = Point3D(),
            rotation : Point3D = Point3D(),
            scale : Point3D = Point3D(1f,1f,1f),
            color : Int = Color.GRAY): Furniture(position,rotation, scale, color){
    init {
        unityFuncName = "addNewChair"
        var type: String = "Chair"
    }

    override fun draw(size: Size): Path {
        val path = Path()
        val margin = 15f
        //seat
        path.addRoundRect(
            ((scale.x * size.width*2)/9)+margin,
            (scale.z * size.height*2)/9 + margin,
            ((scale.x *7* size.width)/9)+margin,
            (scale.z * size.height) + margin,
            size.width.toFloat()/4 ,
            size.height.toFloat()/4,
            Path.Direction.CCW
        )
        path.addRoundRect(
            ((scale.x * size.width*2)/9)+margin,
            (scale.z * size.height*4)/9 + margin,
            ((scale.x *7* size.width)/9)+margin,
            (scale.z * size.height) + margin,
            size.width.toFloat()/4 ,
            size.height.toFloat()/4,
            Path.Direction.CCW
        )
        // hands
        path.addRoundRect(
            ((scale.x * size.width)/9)+margin,
            (scale.z * size.height*4)/9 + margin,
            ((scale.x * size.width*2)/9)+margin,
            (scale.z * size.height) - margin ,
            size.width.toFloat() / 10,
            size.height.toFloat() / 10,
            Path.Direction.CCW
        )
        path.addRoundRect(
            ((scale.x *7* size.width)/9)+margin,
            (scale.z * size.height*4)/9 + margin,
            ((scale.x *8* size.width)/9)+margin,
            (scale.z * size.height) - margin ,
            size.width.toFloat() / 10,
            size.height.toFloat() / 10,
            Path.Direction.CCW
        )
               return path
    }
}