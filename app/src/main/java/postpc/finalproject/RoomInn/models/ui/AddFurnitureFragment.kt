package postpc.finalproject.RoomInn.models.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.furnitureCategoryItem.FurnitureCategoryItemAdapter


class AddFurnitureFragment : Fragment() {
    companion object {
        fun newInstance() = AddFurnitureFragment()
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

        val junkMap = mapOf<String, Drawable>(
            "item 1" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
            "item 2" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
            "item 3" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
            "item 4" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
            "item 5" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
            "item 6" to resources.getDrawable( R.drawable.ic_round_redo_24 ),
        )
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
