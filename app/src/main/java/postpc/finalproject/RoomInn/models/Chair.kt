package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

class Chair(location : Point3D, length : Int = 0, width : Int, height : Int,
    color : String): Furniture(location,length,width, height,   color) {
}