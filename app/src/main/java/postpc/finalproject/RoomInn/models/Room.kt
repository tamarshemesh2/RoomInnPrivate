package postpc.finalproject.RoomInn

import postpc.finalproject.RoomInn.models.Door
import postpc.finalproject.RoomInn.models.Furniture
import postpc.finalproject.RoomInn.models.Window

data class Room (var Corners : MutableList<Point3D> = mutableListOf(),
                 var furniture : MutableList<Furniture> = mutableListOf(),
                 var height : Int = 0,
                 var width : Int = 0,
                 var windows : MutableList<Window> = mutableListOf(),
                 var doors : MutableList<Door> = mutableListOf()) {
}