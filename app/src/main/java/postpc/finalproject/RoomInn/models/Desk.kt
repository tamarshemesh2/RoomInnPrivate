package postpc.finalproject.RoomInn.models

class Desk(location : Point3D, length : Float = 120f, width : Float = 50f, height : Float = 70f,
           color : String): Furniture(location,length,width, height,color) {
}