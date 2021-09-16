package postpc.finalproject.RoomInn.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class RoomsDB(context: Context) {
    val context: Context = context
    private var firebase: FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var user: MutableLiveData<User>
    private var isInitialized: Boolean = false
    var userLoadingStage: MutableLiveData<LoadingStage> = MutableLiveData<LoadingStage>(LoadingStage.SUCCESS)
//    lateinit var Rooms:

    fun isInitialized() : Boolean {
        return isInitialized
    }

    fun createNewUser(id: String) {
        FirebaseApp.initializeApp(context)
        userLoadingStage.value = LoadingStage.LOADING
        var document = firebase.collection("users")
                .document(id)

        // setup new user
        val newUser: User = User()
        newUser.id = id
        user = MutableLiveData(newUser)

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
                user.value = d.toObject(User::class.java)
                isInitialized = true
                userLoadingStage.value = LoadingStage.SUCCESS
            } else {
                createNewUser(id)
            }

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


}