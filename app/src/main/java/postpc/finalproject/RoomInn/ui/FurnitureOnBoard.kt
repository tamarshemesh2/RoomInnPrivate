package postpc.finalproject.RoomInn.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Size
import android.widget.RelativeLayout
import androidx.core.view.GestureDetectorCompat
import com.postpc.myapplication.FurnitureCanvas
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Furniture
import postpc.finalproject.RoomInn.ui.gui_listeners.DragAndScaleListener
import postpc.finalproject.RoomInn.ui.gui_listeners.GeneralGestureListener

@SuppressLint("ClickableViewAccessibility")
class FurnitureOnBoard(
    private val projectViewModel: ProjectViewModel,
    private val context: Context,
    var furniture: Furniture,
    private val board: RelativeLayout,
    coorX: Float,
    coorY: Float
) {

    private lateinit var imageView: FurnitureCanvas

    /* Use GestureDetectorCompat to detect general gesture take place on the image view. */
    private fun addGeneralGestureListener() {
        // This gesture listener is used with the image view.
        val imageViewGestureListener =
            GeneralGestureListener(context, projectViewModel, furniture, board, imageView)

        val imageViewDragGestureListener =
            DragAndScaleListener(context, projectViewModel, furniture, board, imageView)
        // Create image view gesture detector.
        val imageViewGestureDetectorCompat =
            GestureDetectorCompat(context, imageViewGestureListener)
        // Set double tap listener.
        imageViewGestureDetectorCompat.setOnDoubleTapListener(imageViewGestureListener)
        // Set a new OnTouchListener to image view.
        imageView.setOnTouchListener { v, motionEvent -> /* When image view ontouch event occurred, call it's gesture detector's onTouchEvent method. */
            if (!imageViewGestureDetectorCompat.onTouchEvent(motionEvent)) {
                imageViewDragGestureListener.onTouch(v, motionEvent)
            }
            furniture = projectViewModel.furniture!!
            projectViewModel.room.furniture[furniture.id] = furniture
            // Return true to tell android OS this listener has consumed the event, do not need to pass the event to other listeners.
            true
        }
    }

    init {

        createNewImageView()
        board.addView(imageView)
        val params = imageView.layoutParams as RelativeLayout.LayoutParams
        params.leftMargin = coorX.toInt()
        params.topMargin = coorY.toInt()
        params.rightMargin = 0
        params.bottomMargin = 0

        val roomRatio = projectViewModel.room.getRoomRatio()
        params.width = furniture.scale.x.toInt() * roomRatio.toInt()
        params.height = furniture.scale.z.toInt() * roomRatio.toInt()

        params.rightMargin = params.leftMargin + 5 * params.width
        params.bottomMargin = params.topMargin + 10 * params.height

        imageView.rotation = furniture.rotation.y

        imageView.x = -imageView.layoutParams.width.toFloat()
        imageView.y = -2.5f * imageView.layoutParams.height.toFloat()
        addGeneralGestureListener()
    }


    private fun createNewImageView() {
        imageView = FurnitureCanvas(context)
        imageView.setPath(furniture.draw(Size(1, 1)))
        imageView.setPaintColor(furniture.color)
        imageView.setBackgroundColor(Color.TRANSPARENT)
    }

}
