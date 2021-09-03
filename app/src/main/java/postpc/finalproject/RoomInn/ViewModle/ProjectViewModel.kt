package postpc.finalproject.RoomInn.ViewModle

import androidx.lifecycle.ViewModel
import postpc.finalproject.RoomInn.LoadingStage
import postpc.finalproject.RoomInn.Room
import postpc.finalproject.RoomInn.models.Furniture

class ProjectViewModel: ViewModel() {
    lateinit var room : Room
    lateinit var projectName: String
    var loadingStage : LoadingStage = LoadingStage.SUCCESS

    var currentX :Float = 0f
    var currentY : Float = 0f
    var furniture : Furniture? = null
    var newFurniture: Boolean = true


}