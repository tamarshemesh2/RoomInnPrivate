package postpc.finalproject.RoomInn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.RoomCanvas
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Door
import postpc.finalproject.RoomInn.furnitureData.Point3D
import postpc.finalproject.RoomInn.furnitureData.Window
import kotlin.math.roundToInt
import postpc.finalproject.RoomInn.models.RoomInnApplication


class FloorPlanPlacingFragment : Fragment() {
    companion object {

        fun newInstance(): FloorPlanPlacingFragment {
            return FloorPlanPlacingFragment()
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
        // should be the viewModels room!
        return inflater.inflate(R.layout.fragment_floor_plan_no_doors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layout = view.findViewById<RelativeLayout>(R.id.floorPlanLayout)
        val roomCanvas = view.findViewById<RoomCanvas>(R.id.room_canvas)
        val addWindowBtn = view.findViewById<ImageButton>(R.id.addWindowButton)
        val addDoorBtn = view.findViewById<ImageButton>(R.id.addDoorButton)
        val doneFab = view.findViewById<FloatingActionButton>(R.id.done_fab)

        val vto = layout.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                layout.viewTreeObserver
                    .removeOnGlobalLayoutListener(this)
                roomCanvas.setPath(
                    projectViewModel.room.drawFloorPlan(
                        layout.measuredWidth,
                        layout.measuredHeight
                    )
                )
                val offsetToFit = projectViewModel.room.getOffsetToFit(
                    layout.measuredWidth,
                    layout.measuredHeight
                )
                roomCanvas.offsetLeftAndRight(offsetToFit.first.roundToInt())
                roomCanvas.offsetTopAndBottom(offsetToFit.second.roundToInt())
                roomCanvas.setPath(
                    projectViewModel.room.drawFloorPlan(
                        layout.measuredWidth,
                        layout.measuredHeight
                    )
                )
                for (door in projectViewModel.doorsAndWindows){
                    FurnitureOnBoard(
                        projectViewModel,
                        requireContext(),
                        door.furniture,
                        layout,
                        door.furniture.position.x,
                        door.furniture.position.z)
                }
                val location = Point3D(layout.measuredWidth / 2f, 0f, layout.measuredHeight / 2f)

                addDoorBtn.setOnClickListener {
                    projectViewModel.doorsAndWindows += FurnitureOnBoard(
                        projectViewModel,
                        requireContext(),
                        Door(position = location),
                        layout,
                        location.x,
                        location.z
                    )
                }
                addWindowBtn.setOnClickListener {
                    projectViewModel.doorsAndWindows += FurnitureOnBoard(
                        projectViewModel,
                        requireContext(),
                        Window(position = location),
                        layout,
                        location.x,
                        location.z
                    )
                }
                doneFab.setOnClickListener {
                    for (item in projectViewModel.doorsAndWindows){
                        if (item.furniture.type=="Door"){
                            projectViewModel.room.doors += item.furniture as Door
                        } else{
                            projectViewModel.room.windows += item.furniture as Window
                        }
                    }
                    Navigation.findNavController(view).navigate(R.id.action_floorPlanPlacingFragment_to_floorPlanFragment)
                }


            }
        })

    }


//        val findViewById = findViewById<View>(R.id.canvas)
//        findViewById.draw(room.drawFloorPlan())

}
//}