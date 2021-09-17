package postpc.finalproject.RoomInn.ui.projectItem

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import postpc.finalproject.RoomInn.R
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.models.RoomInnApplication


class ProjectItemAdapter : RecyclerView.Adapter<ProjectItemHolder>() {

    private var _projects: MutableList<ProjectItem> = ArrayList()
    private var viewModel: ProjectViewModel? =null

    init {
        setItems()
    }

    fun setItems() {
        if (RoomInnApplication.getInstance().getRoomsDB().isInitialized()) {
            _projects.clear()
            val roomsNamesSet = RoomInnApplication.getInstance().getRoomsDB().rooms.value!!
            roomsNamesSet.forEach {
                _projects.add(ProjectItem(it))
            }
            Log.d("Rooms List: ", "list form adapter is ${roomsNamesSet}.")
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectItemHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectItemHolder, position: Int) {
        val projectItem = _projects[position]
        holder.projectName.text = projectItem.roomName


        holder.editFabButton.setOnClickListener {
            RoomInnApplication.getInstance().getRoomsDB().loadRoomByName(projectItem.roomName, viewModel)
        }

    //TODO:
    // 1. add option to edit the name of the project.
    // 2. add the 'play' button on click (after we create the play VR & fragment).


    }

    override fun getItemCount(): Int {
        return _projects.size
    }

    fun setViewModel(viewModel: ProjectViewModel) {
        this.viewModel = viewModel
    }

}