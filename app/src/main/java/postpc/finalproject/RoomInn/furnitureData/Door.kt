package postpc.finalproject.RoomInn.furnitureData

import java.util.*

abstract class Door(val position : Point3D,
                    val rotation : Point3D,
                    val scale : Point3D,
                    val id: String = UUID.randomUUID().toString())