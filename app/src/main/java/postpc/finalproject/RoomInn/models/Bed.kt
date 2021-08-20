package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

class Bed(location : Point3D, length : Int = 190, width : Int = 90, height : Int = 40,
          color : String): Furniture(location,length,width, height,color)
{
}