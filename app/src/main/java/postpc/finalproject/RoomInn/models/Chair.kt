package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

class Chair(location : Point3D, length : Int = 40 , width : Int = 50, height : Int = 100,
    color : String): Furniture(location,length,width, height,color) {
}