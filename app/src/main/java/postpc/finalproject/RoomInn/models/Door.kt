package postpc.finalproject.RoomInn.models

import postpc.finalproject.RoomInn.Point3D

data class Door(var location : Point3D,
                var length : Int = 190,
                var width : Int = 80) {
}