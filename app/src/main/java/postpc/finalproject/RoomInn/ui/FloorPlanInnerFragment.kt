package postpc.finalproject.RoomInn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ProgressBar
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.RoomCanvas
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import kotlin.math.roundToInt
import postpc.finalproject.RoomInn.models.RoomInnApplication


class FloorPlanInnerFragment : Fragment() {
    //2
    companion object {

        fun newInstance(): FloorPlanInnerFragment {
            return FloorPlanInnerFragment()
        }
    }

    private val projectViewModel: ProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)
    }

    //3
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        room.drawFloorPlan()
        // should be the viewModels room!
        return inflater.inflate(R.layout.fragment_inner_floor_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layout = view.findViewById<RelativeLayout>(R.id.room_layout)
        val roomCanvas = view.findViewById<RoomCanvas>(R.id.room_canvas)
        val loadingBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        loadingBar.visibility = View.VISIBLE
        val vto = layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                layout.viewTreeObserver
                    .removeOnGlobalLayoutListener(this)
                roomCanvas.setPath(projectViewModel.room.drawFloorPlan(layout.measuredWidth, layout.measuredHeight))
                loadingBar.visibility = View.GONE
                val offsetToFit = projectViewModel.room.getOffsetToFit(layout.measuredWidth,layout.measuredHeight)
                roomCanvas.offsetLeftAndRight(offsetToFit.first.roundToInt())
                roomCanvas.offsetTopAndBottom(offsetToFit.second.roundToInt())
                roomCanvas.setPath(projectViewModel.room.drawFloorPlan(layout.measuredWidth, layout.measuredHeight))

                for (furId in RoomInnApplication.getInstance().getRoomsDB().roomToFurnitureMap[projectViewModel.room.id]!!) {
                    val fur = RoomInnApplication.getInstance().getRoomsDB().furnitureMap[furId]!!
                    val t= FurnitureOnBoard(
                        projectViewModel,
                        requireContext(),
                        fur,
                        layout,
                        fur.position.x,
                        fur.position.z
                    )
                }
            }
        })

    }


//        val findViewById = findViewById<View>(R.id.canvas)
//        findViewById.draw(room.drawFloorPlan())

}
//}