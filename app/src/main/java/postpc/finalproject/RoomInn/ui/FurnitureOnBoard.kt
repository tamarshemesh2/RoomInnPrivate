package postpc.finalproject.RoomInn.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import postpc.finalproject.RoomInn.models.Furniture

//import postpc.finalproject.RoomInn.databinding.ActivityMainBinding
import android.widget.RelativeLayout

import android.view.View.OnTouchListener
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.Navigation
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.RotateZoomImageViewFurniture
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.models.Point3D


@SuppressLint("ClickableViewAccessibility")
class FurnitureOnBoard(
    private val projectViewModel: ProjectViewModel,
    private val context: Context,
    var furniture: Furniture,
    private val drawableFurniture: Drawable,
    private val board: RelativeLayout,
    coorX: Float,
    coorY: Float
) {

    private lateinit var freeImageView: RotateZoomImageViewFurniture
    private lateinit var lockImageView: ImageView

    init {
        createNewImageView()
        if (projectViewModel.roomEnableFurnitureOnBoard) {
            board.addView(freeImageView)
            freeImageView.layoutParams.width = furniture.width.toInt()
            freeImageView.layoutParams.height = furniture.length.toInt()

            freeImageView.x = -freeImageView.layoutParams.width.toFloat()
            freeImageView.y = -1.5f*freeImageView.layoutParams.height.toFloat()

            freeImageView.rotation = furniture.rotation.toFloat()
            freeImageView.setPlace(coorX, coorY)
            freeImageView.setOnTouchListener(OnTouchListener { v, event ->
                freeImageView.onTouch(v, event)
                furniture.scale(freeImageView.scaleDiff)
                furniture.rotation = (freeImageView.rotation).toInt() % 360
                furniture.location = Point3D(event.rawX , event.rawY , 0f)
                return@OnTouchListener true
            })
        } else {
            board.addView(lockImageView)
            val params = lockImageView.layoutParams as RelativeLayout.LayoutParams
            params.leftMargin = coorX.toInt()
            params.topMargin = coorY.toInt()
            params.rightMargin = 0
            params.bottomMargin = 0

            params.width = furniture.width.toInt()
            params.height = furniture.length.toInt()

            params.rightMargin = params.leftMargin + 5 * params.width
            params.bottomMargin = params.topMargin + 10 * params.height

            lockImageView.rotation = furniture.rotation.toFloat()

            lockImageView.x = -lockImageView.layoutParams.width.toFloat()
            lockImageView.y = -1.5f*lockImageView.layoutParams.height.toFloat()
            lockImageView.setOnClickListener {
                projectViewModel.furniture = furniture
                projectViewModel.newFurniture = false
                Navigation.findNavController(board)
                    .navigate(R.id.action_floorPlanFragment_to_editFurnitureFragment)
            }
        }
    }

    private fun createNewImageView() {
        freeImageView = RotateZoomImageViewFurniture(context)
        lockImageView = ImageView(context)

        lockImageView.setImageDrawable(drawableFurniture)
        freeImageView.setImageBitmap(drawableFurniture.toBitmap())

        lockImageView.setColorFilter(furniture.color)
        freeImageView.setColorFilter(furniture.color)
        freeImageView.setBackgroundColor(Color.BLACK)
        lockImageView.setBackgroundColor(Color.BLACK)

    }

}
