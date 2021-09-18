package postpc.finalproject.RoomInn.ui.gui_listeners

import android.content.Context
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.widget.RelativeLayout
import androidx.navigation.Navigation
import postpc.finalproject.RoomInn.FurnitureCanvas
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Furniture

class GeneralGestureListener(
    private var context: Context?,
    private var projectViewModel: ProjectViewModel,
    private var furniture: Furniture,
    private var board: RelativeLayout,
    private val imageView: FurnitureCanvas
) :
    SimpleOnGestureListener() {

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        projectViewModel.newFurniture = false
        if (furniture.type in listOf("Door", "Window")) {
            Navigation.findNavController(board)
                .navigate(R.id.action_floorPlanPlacingFragment_to_editFurnitureFragment)
        } else {
            Navigation.findNavController(board)
                .navigate(R.id.action_floorPlanFragment_to_editFurnitureFragment)
        }
        return true
    }

    override fun onLongPress(e: MotionEvent) {
    }

}
