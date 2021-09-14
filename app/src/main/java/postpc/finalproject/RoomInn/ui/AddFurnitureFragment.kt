package postpc.finalproject.RoomInn.ui

import android.annotation.SuppressLint
import android.graphics.Path
import android.os.Bundle
import android.util.Size
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Bed
import postpc.finalproject.RoomInn.furnitureData.Chair
import postpc.finalproject.RoomInn.furnitureData.Closet
import postpc.finalproject.RoomInn.furnitureData.Desk
import postpc.finalproject.RoomInn.ui.furnitureCategoryItem.FurnitureCategoryItemAdapter

class AddFurnitureFragment : Fragment() {
    companion object {
        fun newInstance() = AddFurnitureFragment()
    }

    private val projectViewModel: ProjectViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ProjectViewModel::class.java)
    }

    private val adapter: FurnitureCategoryItemAdapter = FurnitureCategoryItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: finish finding all the views
        // find all views
        val furnitureRecyclerView: RecyclerView = view.findViewById(R.id.furniture_recycler)
        val size = Size(250, 250)
        val draw = Bed().draw(size)
        val junkMap = mapOf<String, Path>(
            "bed 1" to (draw),
            "chair" to (Chair().draw(size)),
            "closet" to (Closet().draw(size)),
            "desk" to (Desk().draw(Size(250,120))),
            "bed 5" to (draw),
            "bed 6" to (draw),
        )
        adapter.setViewModel(projectViewModel)
        adapter.setContext(requireContext())
        adapter.setItems(junkMap)

        // set the recycle view
        furnitureRecyclerView.adapter = adapter
        furnitureRecyclerView.layoutManager =
            GridLayoutManager(requireActivity(), 2, RecyclerView.VERTICAL, false)
        furnitureRecyclerView.layoutManager
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_furniture, container, false)
    }

}
