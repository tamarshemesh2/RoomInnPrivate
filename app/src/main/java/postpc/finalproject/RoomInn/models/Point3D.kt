package postpc.finalproject.RoomInn

data class Point3D(var x: Int = 0,
                   var y: Int = 0,
                   var z: Int = 0) {

    fun add(second : Point3D) {
        x += second.x
        y += second.y
        z += second.y
    }


}