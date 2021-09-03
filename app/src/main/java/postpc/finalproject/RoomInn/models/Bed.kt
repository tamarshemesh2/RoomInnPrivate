package postpc.finalproject.RoomInn.models

class Bed(location : Point3D, length : Float = 190f, width : Float = 90f, height : Float = 40f,
          color : String): Furniture(location,length,width, height,color)
{
    override fun scale(scaleFactor: Float): Point3D {
        length *=scaleFactor
        width *=scaleFactor
        return Point3D(width,length,height)
    }
}