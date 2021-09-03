package postpc.finalproject.RoomInn.models

import android.graphics.Color

class Desk(location : Point3D, length : Float = 120f, width : Float = 50f, height : Float = 70f,
           color : String= Color.GRAY.toString()): Furniture(location,length,width, height,color) {
}