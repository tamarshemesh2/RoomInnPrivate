package postpc.finalproject.RoomInn.models

import android.graphics.Color

class Bed(location : Point3D, length : Float = 190f, width : Float = 90f, height : Float = 40f,
          color : Int = Color.GRAY): Furniture(location,length,width, height,color)
{
    override fun scale(scaleFactor: Float): Point3D {
        length *=scaleFactor
        width *=scaleFactor
        return Point3D(width,length,height)
    }
}