package postpc.finalproject.RoomInn.models

class Chair(location : Point3D, length : Float = 40f, width : Float = 50f, height : Float = 100f,
            color : String): Furniture(location,length,width, height,color) {
}