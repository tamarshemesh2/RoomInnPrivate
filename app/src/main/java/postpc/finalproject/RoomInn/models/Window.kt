package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

data class Window(var location : Point3D,
                  var length : Int = 60,
                  var width : Int = 80) {
}