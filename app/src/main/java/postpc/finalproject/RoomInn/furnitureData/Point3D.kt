package postpc.finalproject.RoomInn.furnitureData

data class Point3D(var x: Float = 0f,
                   var y: Float = 0f,
                   var z: Float = 0f) {

    constructor(copy : Point3D) : this() {
        this.x = copy.x
        this.y = copy.y
        this.z = copy.z
    }

    fun add(step : Point3D) : Point3D {
        x += step.x
        y += step.y
        z += step.y

        return this
    }

    fun multiply(scaleFactor : Float) : Point3D {
        x *= scaleFactor
        y *= scaleFactor
        z *= scaleFactor

        return this
    }


}