package postpc.finalproject.RoomInn.models

import android.graphics.Color

class Closet(location : Point3D, length : Float = 100f, width : Float = 50f, height : Float = 200f,
             color : Int = Color.GRAY): Furniture(location,length,width, height,color) {
}