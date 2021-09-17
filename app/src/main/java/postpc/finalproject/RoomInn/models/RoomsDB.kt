package postpc.finalproject.RoomInn.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import postpc.finalproject.RoomInn.Room
import postpc.finalproject.RoomInn.ViewModle.ProjectViewModel
import postpc.finalproject.RoomInn.furnitureData.Point3D

class RoomsDB(context: Context) {
    val context: Context = context
    private var firebase: FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var user: User
    private var isInitialized: Boolean = false
    lateinit var rooms: MutableLiveData<MutableList<String>>  // live data fo the rooms, containing copy of the users roomsSet
    var userLoadingStage: MutableLiveData<LoadingStage> = MutableLiveData<LoadingStage>(LoadingStage.SUCCESS)
    var roomsListenerLambda: () -> Unit = {}
    var loadRoomNavLambda: () -> Unit = {}
    var roomsMap: MutableMap<String, Room> = mutableMapOf()
//    lateinit var Rooms:

    fun isInitialized() : Boolean {
        return isInitialized
    }

    fun createNewUser(id: String) {
        FirebaseApp.initializeApp(context)
        userLoadingStage.value = LoadingStage.LOADING
        var document = firebase.collection("users")
                .document(id)

        //TODO: remove this line!
        val room1 = Room()
        room1.Corners = mutableListOf(
                Point3D(5f, 5f, 5f),
                Point3D(287.5f, 5f, 5f),
                Point3D(287.5f, 5f, 152f),
                Point3D(5f, 5f, 152f)
        )
        room1.name = "project 1"
        val room2 = Room()
        room2.Corners = mutableListOf(
                Point3D(5f, 5f, 5f),
                Point3D(100f, 5f, 5f),
                Point3D(100f, 5f, 152f),
                Point3D(5f, 5f, 152f)
        )
        room2.name = "project 2"
        val room3 = Room()
        room3.Corners = mutableListOf(
                Point3D(5f, 5f, 5f),
                Point3D(200f, 5f, 5f),
                Point3D(200f, 5f, 152f),
                Point3D(5f, 5f, 152f)
        )
        room3.name = "project 3"
        room3.userId = user.id
        room2.userId = user.id
        room1.userId = user.id
        createNewRoom(room1)
        createNewRoom(room2)
        createNewRoom(room3)
        Log.d("Rooms List: ", "list form DB is ${rooms.value}.")


        document.set(user)
                .addOnSuccessListener {
                    isInitialized = true
                    Log.d("Firebase", "loading user data succeeded")
                    userLoadingStage.value = LoadingStage.SUCCESS
                }
                .addOnFailureListener {
                    userLoadingStage.value = LoadingStage.FAILURE
                    Log.d("Firebase", "loading user data failed")
                }
                .addOnCanceledListener {
                    userLoadingStage.value = LoadingStage.SUCCESS
                    Log.d("Firebase", "loading user data failed")
                }
    }

    fun initialize(id: String) {

        FirebaseApp.initializeApp(context)
        userLoadingStage.value = LoadingStage.LOADING
        var document = firebase.collection("users")
                .document(id)
        document.get().addOnSuccessListener { d: DocumentSnapshot ->
            if (d.exists()) {
                user = d.toObject(User::class.java)!!
                rooms = MutableLiveData(user.roomsList)
                isInitialized = true
                userLoadingStage.value = LoadingStage.SUCCESS
            } else {
                user = User(id=id)
                rooms = MutableLiveData(user.roomsList)
                createNewUser(id)
                isInitialized = true
            }
            setRoomsListListener()

        }
                .addOnFailureListener {
                    userLoadingStage.value = LoadingStage.FAILURE
                    Log.d("Firebase", "loading user data failed")
                }
                .addOnCanceledListener {
                    userLoadingStage.value = LoadingStage.SUCCESS
                    Log.d("Firebase", "loading user data failed")
                }

    }

    fun updateRoom(room: Room) {
        firebase.collection("rooms").document(room.id).set(room)
    }

    fun createNewRoom(room: Room) {
        updateRoom(room)
        rooms.value!!.add(room.name)
        Log.d("Rooms List: ", "list form createRoom is ${rooms.value}.")
    }

    fun setRoomsListListener() {
        return rooms.observeForever {
            user.roomsList = it
            roomsListenerLambda()
            firebase.collection("users").document(user.id).set(user)
        }
    }

    /**
     * This function loade the roon into the room map, and updates the view model to hold the current room.
     */
    fun loadRoomByName(roomName: String, viewModel: ProjectViewModel ?= null) {

        userLoadingStage.value = LoadingStage.LOADING
        if (roomName in roomsMap) {
            if (viewModel != null) {
                viewModel.room = roomsMap[roomName]!!
            }
            loadRoomNavLambda()
            userLoadingStage.value = LoadingStage.SUCCESS
            return

        }
        firebase.collection("rooms").whereEqualTo("userId", user.id).whereEqualTo("name", roomName).get()
                .addOnSuccessListener {
                    val documents = it.documents
                    var room: Room ?= null
                    for (doc in documents) {
                        room = doc.toObject(Room::class.java)
                        if (room != null){
                            roomsMap[roomName] = room
                            // TODO: how do we save the fractures?
                        }
                    }
                    if (viewModel != null && room != null) {
                        viewModel.room = roomsMap[roomName]!!
                    }
                    loadRoomNavLambda()
                    userLoadingStage.value = LoadingStage.SUCCESS
                }
                .addOnFailureListener {
                    userLoadingStage.value = LoadingStage.FAILURE
                }
    }


// TODO: do we need this?

//    fun setRoomNewName(newName: String, oldName: String) {
//        userLoadingStage.value = LoadingStage.LOADING
//        firebase.collection("rooms").whereEqualTo("userID", user.id).whereEqualTo("name", oldName).get()
//                .addOnSuccessListener {
//                    val documents = it.documents
//                    for (doc in documents) {
//                        val room: Room ?= doc.toObject(Room::class.java)
//
//                              ...
//
//                        }
//                    }
//                    userLoadingStage.value = LoadingStage.SUCCESS
//                }
//                .addOnFailureListener {
//                    userLoadingStage.value = LoadingStage.FAILURE
//                }
//    }


}