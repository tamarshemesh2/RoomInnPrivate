package postpc.finalproject.RoomInn.models.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.drawable.Drawable
import postpc.finalproject.RoomInn.models.Furniture

//import postpc.finalproject.RoomInn.databinding.ActivityMainBinding
import android.widget.RelativeLayout

import android.view.View.OnTouchListener
import android.view.animation.LinearInterpolator
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toColor
import androidx.core.view.marginLeft
import postpc.finalproject.RoomInn.RotateZoomImageViewFurniture
import postpc.finalproject.RoomInn.RotateZoomMove


@SuppressLint("ClickableViewAccessibility")
class FurnitureOnBoard(
    private val context: Context,
    var furniture: Furniture,
    private val drawableFurniture: Drawable,
    private val board: RelativeLayout,
    private val coorX: Float,
    private val coorY: Float
) {

    private lateinit var imageView: RotateZoomImageViewFurniture


    init {
        createNewImageView()
        board.addView(imageView)
        imageView.setPlace(coorX,coorY)
        imageView.setOnTouchListener(OnTouchListener { v, event -> imageView.onTouch(v, event) })
    }

    private fun createNewImageView() {
        imageView =
            RotateZoomImageViewFurniture(context)
        imageView.setImageBitmap(drawableFurniture.toBitmap())
        imageView.x = board.x
        imageView.y = board.y
//        coorX - (imageView.width / 2)
        imageView.setColorFilter(Color.parseColor(furniture.color))
//        imageView.setBoardLayout(board)


    }

}
