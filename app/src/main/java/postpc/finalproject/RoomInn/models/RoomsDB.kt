package postpc.finalproject.RoomInn.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class RoomsDB(context: Context) {
    val context: Context = context
    private var firebase: FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var user: MutableLiveData<User>
    var userLoadingStage: MutableLiveData<LoadingStage> = MutableLiveData<LoadingStage>(LoadingStage.SUCCESS)
//    lateinit var Rooms:


    public fun Initiolise(id: String) {
        userLoadingStage.value = LoadingStage.LOADING
        FirebaseApp.initializeApp(context)
        var document = firebase.collection("users")
                .document(id)
        document.get().addOnSuccessListener { d: DocumentSnapshot ->
            if (d.exists()) {
                user.value = d.toObject(User::class.java)
                userLoadingStage.value = LoadingStage.SUCCESS
            }

        }
                .addOnFailureListener {
                    userLoadingStage.value = LoadingStage.FAILURE
                }
    }


}