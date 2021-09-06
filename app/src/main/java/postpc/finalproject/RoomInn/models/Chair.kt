package postpc.finalproject.RoomInn.models

import android.graphics.Color

class Chair(location : Point3D, length : Float = 40f, width : Float = 50f, height : Float = 100f,
            color : Int = Color.GRAY): Furniture(location,length,width, height,color) {
}