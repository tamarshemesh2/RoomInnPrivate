package postpc.finalproject.RoomInn.models.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.models.Bed
import postpc.finalproject.RoomInn.models.Point3D


class FloorPlanFragment : Fragment() {
    var furnitureList:List<FurnitureOnBoard> = listOf()

    companion object {
        fun newInstance() = FloorPlanFragment()
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set the view direction -> LTR
        this.activity?.window?.decorView?.layoutDirection = View.LAYOUT_DIRECTION_LTR;

        // TODO: finish finding all the views
        // find all views
        val canvas: RelativeLayout = view.findViewById(R.id.floorPlan)// Your Viewgroup
        val addFab: FloatingActionButton = view.findViewById(R.id.addButton)
        var toAddFurniture: Boolean = true
        canvas.setOnTouchListener { v, event ->
            if (toAddFurniture){
            furnitureList+=FurnitureOnBoard(this.requireContext(), Bed(Point3D(),color = "#FFFFFFFF")
                ,resources.getDrawable(R.drawable.ic_baseline_edit_24),canvas,event.x,event.y); }
            toAddFurniture=false
            return@setOnTouchListener true
        }
        addFab.setOnClickListener {
            toAddFurniture=true
            Toast.makeText(this.requireContext(),"Tap where you wish to add new furniture", Toast.LENGTH_LONG).show();


        }


    }


}
