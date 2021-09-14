package postpc.finalproject.RoomInn.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import postpc.finalproject.RoomInn.Room

data class User(
        val id: String = "",
        val rooms: MutableLiveData<List<Room>> = MutableLiveData<List<Room>>()
) {
}