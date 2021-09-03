package postpc.finalproject.RoomInn.models.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItemAdapter


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

        val junkMap = mapOf<String, Int>(
            "bed 1" to  (R.drawable.ic_round_redo_24) ,
            "bed 2" to ( R.drawable.ic_round_redo_24 ),
            "bed 3" to ( R.drawable.ic_round_redo_24 ),
            "bed 4" to ( R.drawable.ic_round_redo_24 ),
            "bed 5" to ( R.drawable.ic_round_redo_24 ),
            "bed 6" to ( R.drawable.ic_round_redo_24 ),
        )
        adapter.setViewModel(projectViewModel)
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
