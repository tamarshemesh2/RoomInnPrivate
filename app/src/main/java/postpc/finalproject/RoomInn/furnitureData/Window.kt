package postpc.finalproject.RoomInn.furnitureData

import postpc.finalproject.RoomInn.furnitureData.Point3D
import java.util.*


abstract class Window(val position : Point3D,
                      val rotation : Point3D,
                      val scale : Point3D,
                      val id: String = UUID.randomUUID().toString()) {}