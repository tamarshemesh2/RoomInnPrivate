package postpc.finalproject.RoomInn.ViewModle

import androidx.lifecycle.ViewModel
import com.facebook.CallbackManager

class LoginViewModel : ViewModel(){
    var callbackManager: CallbackManager = CallbackManager.Factory.create()
}