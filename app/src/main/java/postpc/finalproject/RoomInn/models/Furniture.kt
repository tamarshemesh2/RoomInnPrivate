package postpc.finalproject.RoomInn.models

import android.graphics.Color
import postpc.finalproject.RoomInn.Point3D

abstract class Furniture(var location : Point3D,
                     var length : Int = 0,
                     var width : Int = 0,
                     var height : Int = 0,
                     var color : String = "FFFFFFFF") {
}