package postpc.finalproject.RoomInn.models

import androidx.lifecycle.MutableLiveData
import postpc.finalproject.RoomInn.Room

data class User(
        var id: String = "not initialised id",
        val rooms: MutableLiveData<List<Room>> = MutableLiveData<List<Room>>()
) {
}