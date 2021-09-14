package postpc.finalproject.RoomInn.ui.gui_listeners

import android.annotation.SuppressLint
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.RelativeLayout
import com.postpc.myapplication.FurnitureCanvas
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Furniture
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * The scale listener, used for handling multi-finger scale gestures.
 */
class DragAndScaleListener(
    private var context: Context?,
    private var projectViewModel: ProjectViewModel,
    private var furniture: Furniture,
    private var board: RelativeLayout,
    private val imageView: FurnitureCanvas
) : OnTouchListener {
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2

    private var mode = NONE
    private var oldDist = 1f
    private var d = 0f
    var newRot = 0f
    var scaleDiff = 0f

    var params = imageView.layoutParams as RelativeLayout.LayoutParams
    var startWidth = 0
    var startHeight = 0
    var dx = 0f
    var dy = 0f
    var x = 0f
    var y = 0f
    var angle = 0f


    private fun spacing(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x + y * y).toDouble()).toFloat()
    }

    private fun rotation(event: MotionEvent): Float {
        val delta_x = (event.getX(0) - event.getX(1)).toDouble()
        val delta_y = (event.getY(0) - event.getY(1)).toDouble()
        val radians = atan2(delta_y, delta_x)
        return (Math.toDegrees(radians) / 90).roundToInt().toFloat() * 90
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val view = v as ImageView
        projectViewModel.furniture = furniture
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                params = view.layoutParams as RelativeLayout.LayoutParams
                startWidth = params.width
                startHeight = params.height
                dx = event.rawX - params.leftMargin
                dy = event.rawY - params.topMargin
                mode = DRAG
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                if (oldDist > 10f) {
                    mode = ZOOM
                }
                d = rotation(event)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> mode = NONE
            MotionEvent.ACTION_MOVE -> if (mode == DRAG) {
                x = event.rawX
                y = event.rawY
                params.leftMargin = ((x - dx).toInt())
                params.topMargin = ((y - dy).toInt())
                furniture.position.x = (x - dx)
                furniture.position.z = (y - dy)
                params.rightMargin = 0
                params.bottomMargin = 0
                params.rightMargin = params.leftMargin + 5 * params.width
                params.bottomMargin = params.topMargin + 10 * params.height
                fixing()
                view.layoutParams = params
            } else if (mode == ZOOM) {

                if (event.pointerCount == 2) {
                    newRot = rotation(event)
                    val r = newRot - d
                    angle = r
                    x = event.rawX
                    y = event.rawY
                    val newDist = spacing(event)
                    if (newDist > 10f) {
                        val scale = newDist / oldDist * view.scaleX
                        if (scale > 0.6) {
                            scaleDiff = scale
                            view.scaleX = scale
                            view.scaleY = scale
                            furniture.scale(scale)
                        }
                    }
                    x = event.rawX
                    y = event.rawY
                    params.leftMargin = (x - dx + scaleDiff).toInt()
                    params.topMargin = (y - dy + scaleDiff).toInt()
                    params.rightMargin = 0
                    params.bottomMargin = 0
                    params.rightMargin = params.leftMargin + 5 * params.width
                    params.bottomMargin = params.topMargin + 10 * params.height
                    fixing()
                    view.layoutParams = params
                }
            }
        }
        projectViewModel.furniture = furniture
        return true
    }


    private fun fixing() {
        if (params.leftMargin < 0) {
            params.leftMargin = 0
            params.rightMargin = params.leftMargin + 5 * params!!.width
        }
        if (params.topMargin < 0) {
            params.topMargin = 0
            params.bottomMargin = params.topMargin + 10 * params!!.height
        }
        if (params.bottomMargin < 0) {
            params.bottomMargin = 0
            params.topMargin = 10 * params.height
        }
        if (params.rightMargin < 0) {
            params.rightMargin = 0
            params.leftMargin = 5 * params.height
        }
    }
}


