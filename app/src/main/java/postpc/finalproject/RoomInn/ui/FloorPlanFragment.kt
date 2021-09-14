package postpc.finalproject.RoomInn.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout

import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import java.util.*


class FloorPlanFragment : Fragment() {
    var furnitureList: List<FurnitureOnBoard> = listOf()
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var hamburger: ImageView;

    companion object {
        fun newInstance() = FloorPlanFragment()
    }

    private val projectViewModel: ProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_floor_plan, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set the view direction -> LTR
        this.activity?.window?.decorView?.layoutDirection = View.LAYOUT_DIRECTION_LTR;

        // TODO: finish finding all the views
        // find all views
        val roomCanvas: View? = view.findViewById(R.id.floorPlan)
        val roomLayout: RelativeLayout = view.findViewById(R.id.floorPlanLayout)// Your Viewgroup

        val addFab: ImageButton = view.findViewById(R.id.addButton)
        var toAddFurniture = false
        //add all furniture to board

        // find hamburger views:
        drawerLayout = view.findViewById(R.id.draw_layout)
        navigationView = view.findViewById(R.id.hamburger_settings_navigation_layout)
        hamburger = view.findViewById(R.id.hamburgerMenuButton)


        roomCanvas!!.setOnTouchListener { v, event ->
            if (toAddFurniture) {
                projectViewModel.currentX = event.rawX - roomCanvas.x
                projectViewModel.currentY = event.rawY - roomCanvas.y
                projectViewModel.newFurniture = true
                Navigation.findNavController(v)
                    .navigate(R.id.action_floorPlanFragment_to_addFurnitureFragment2)
            }
            toAddFurniture = false
            return@setOnTouchListener true
        }
        addFab.setOnClickListener {
            toAddFurniture = true
            Toast.makeText(
                this.requireContext(),
                "Tap where you wish to place a new furniture",
                Toast.LENGTH_SHORT
            ).show();
        }

        hamburger.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }


}
