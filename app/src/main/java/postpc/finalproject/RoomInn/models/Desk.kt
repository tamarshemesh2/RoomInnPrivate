package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

class Desk(location : Point3D, length : Int = 120, width : Int = 50, height : Int = 70,
           color : String): Furniture(location,length,width, height,color) {
}