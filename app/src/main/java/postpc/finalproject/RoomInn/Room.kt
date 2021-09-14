package postpc.finalproject.RoomInn

import android.graphics.Path
import android.util.Log
import android.util.Size
import com.postpc.myapplication.furnitureData.Wall
import postpc.finalproject.RoomInn.furnitureData.Door
import postpc.finalproject.RoomInn.furnitureData.Furniture
import postpc.finalproject.RoomInn.furnitureData.Point3D
import postpc.finalproject.RoomInn.furnitureData.Window


data class Room(
    var Corners: MutableList<Point3D> = mutableListOf(),
    var Walls: MutableList<Wall> = mutableListOf(),
    var furniture: MutableMap<String, Furniture> = mutableMapOf(),
    var windows: MutableList<Window> = mutableListOf(),
    var doors: MutableList<Door> = mutableListOf(),
    private var displayRatio: Float = 1f,
) {
    private var minX = 0f
    private var minZ = 0f

    private fun getRoomSize(): Size {
        minX = Corners.first().x
        var maxX = Corners.first().x
        minZ = Corners.first().z
        var maxZ = Corners.first().z
        for (corner in Corners) {
            if (corner.x > maxX) {
                maxX = corner.x
            } else if (corner.x < minX) {
                minX = corner.x
            }
            if (corner.z > maxZ) {
                maxZ = corner.z
            } else if (corner.z < minZ) {
                minZ = corner.z
            }
        }
        return Size((maxX - minX).toInt(), (maxZ - minZ).toInt())
    }

    fun setRoomRatio(boardSize: Size) {
        val roomSize = getRoomSize()
        displayRatio = minOf(
            (boardSize.width - 30) / roomSize.width.toFloat(),
            boardSize.height / roomSize.height.toFloat()
        )
    }

    fun getRoomRatio(): Float {
        return displayRatio
    }

    fun drawFloorPlan(boardWidth: Int = 0, boardHeight: Int = 0): Path {
        Log.d("yuval", "width: $boardWidth, height: $boardHeight")
        val path = Path()
        if (boardWidth != 0) {
            setRoomRatio(Size(boardWidth, boardHeight))
        }
        if (Corners.size > 0) {
            val last = Corners.last()
            path.moveTo((last.x - minX) * displayRatio, (last.z - minZ) * displayRatio)
            for (point in Corners) {
                path.lineTo((point.x - minX) * displayRatio, (point.z - minZ) * displayRatio)
                path.moveTo((point.x - minX) * displayRatio, (point.z - minZ) * displayRatio)
            }
        }
        return path
    }
}
