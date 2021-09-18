package postpc.finalproject.RoomInn.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.models.RoomInnApplication
import postpc.finalproject.RoomInn.models.RoomsDB
import top.defaults.colorpicker.ColorPickerPopup

import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver


class EditFurnitureFragment : Fragment() {
    companion object {
        fun newInstance() = EditFurnitureFragment()
    }

    private val projectViewModel: ProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // find all the views

        val colorBtn: ImageView = view.findViewById(R.id.color_btn)
        val widthEditText = view.findViewById<EditText>(R.id.width_edit_text)
        val heightEditText = view.findViewById<EditText>(R.id.heigh_edit_text)
        val lengthEditText = view.findViewById<EditText>(R.id.length_edit_text)
        val locationText = view.findViewById<TextView>(R.id.location_inner_text)
        val rotateText = view.findViewById<TextView>(R.id.rotate_inner_text)
        val rotateBtn = view.findViewById<ImageButton>(R.id.rotate_btn)

        val freeRatioCheckBox = view.findViewById<CheckBox>(R.id.enable_ratio_checkbox)
        val furnitureImageView = view.findViewById<ImageView>(R.id.furniture_img)
        val saveFab = view.findViewById<FloatingActionButton>(R.id.save_fab)

        val furniture = projectViewModel.furniture!!


        colorBtn.setColorFilter(furniture.color)
        locationText.text = " ${furniture.position.x} , ${furniture.position.z}"
        widthEditText.setText(furniture.scale.x.toString())
        lengthEditText.setText(furniture.scale.z.toString())
        heightEditText.setText(furniture.scale.y.toString())
        rotateText.text = furniture.rotation.y.toString()
        freeRatioCheckBox.isChecked = projectViewModel.roomEnableFurnitureOnBoard
        //todo : choose furniture img according to type


        colorBtn.setOnClickListener { v ->
            ColorPickerPopup.Builder(context).initialColor(furniture.color)
                .enableBrightness(true) // enable color brightness
                .enableAlpha(false) // enable color alpha
                .okTitle("Choose") // this is top right
                .cancelTitle("Cancel") // this is top left
                .showIndicator(true) // this is the small box
                .showValue(true).build().show(v,
                    object : ColorPickerObserver() {
                        override fun onColorPicked(color: Int) {
                            colorBtn.setColorFilter(color)
                            furniture.color = color
                        }
                    })
        }
        freeRatioCheckBox.setOnClickListener {
            val bool = freeRatioCheckBox.isChecked
            widthEditText.isEnabled = bool
            lengthEditText.isEnabled = bool
            heightEditText.isEnabled = bool
            furniture.freeScale = bool
        }

        rotateBtn.setOnClickListener {
            furniture.rotation.y = (furniture.rotation.y + 90) % 360
            rotateText.text = furniture.rotation.y.toString()
        }

        saveFab.setOnClickListener {
            furniture.scale.y = heightEditText.text.toString().toFloat()
            furniture.scale.z = lengthEditText.text.toString().toFloat()
            furniture.scale.x = widthEditText.text.toString().toFloat()
            projectViewModel.furniture = furniture

            // update the furniture in the DB
            var DB: RoomsDB = RoomInnApplication.getInstance().getRoomsDB()
            DB.furnitureMap[projectViewModel.furniture!!.id] = projectViewModel.furniture!!
            if (projectViewModel.furniture!!.id !in DB.roomToFurnitureMap[projectViewModel.room.id]!!) {
                DB.roomToFurnitureMap[projectViewModel.room.id]!!.add(projectViewModel.furniture!!.id)
            }

            projectViewModel.roomEnableFurnitureOnBoard = freeRatioCheckBox.isChecked

            Navigation.findNavController(it)
                .navigate(R.id.action_editFurnitureFragment_to_floorPlanFragment)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_furniture, container, false)
    }

}
