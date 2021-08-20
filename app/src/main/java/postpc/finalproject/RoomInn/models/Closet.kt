package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

class Closet(location : Point3D, length : Int = 100, width : Int = 50, height : Int = 200,
              color : String): Furniture(location,length,width, height,color) {
}