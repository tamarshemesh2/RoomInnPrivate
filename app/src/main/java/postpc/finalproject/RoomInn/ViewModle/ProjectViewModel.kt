package postpc.finalproject.RoomInn.ViewModle

import androidx.lifecycle.ViewModel
import postpc.finalproject.RoomInn.Room
import postpc.finalproject.RoomInn.furnitureData.Furniture
import postpc.finalproject.RoomInn.models.LoadingStage

class ProjectViewModel: ViewModel() {
    var roomEnableFurnitureOnBoard: Boolean = false
    var room : Room = Room()
    lateinit var projectName: String
    var loadingStage : LoadingStage = LoadingStage.SUCCESS

    var currentX :Float = 0f
    var currentY : Float = 0f
    var furniture : Furniture? = null
    var newFurniture: Boolean = true
}