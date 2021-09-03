package postpc.finalproject.RoomInn.models

data class Point3D(var x: Float = 0f,
                   var y: Float = 0f,
                   var z: Float = 0f) {

    fun add(step : Point3D) {
        x += step.x
        y += step.y
        z += step.y
    }


}