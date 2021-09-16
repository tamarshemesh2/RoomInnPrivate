package postpc.finalproject.RoomInn;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import postpc.finalproject.RoomInn.ViewModle.DBViewModel;
import postpc.finalproject.RoomInn.models.RoomInnApplication;

public class MainActivity extends AppCompatActivity {
    private String userId = null;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            userId = acct.getId();
            Log.d("login", "login with google id: " + userId);
        }

        // user  is already logged in with facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            userId = accessToken.getUserId();
            Log.d("login", "login with facebook id: " + userId);
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
            Log.d("login", "login with firebase id: " + userId);

        }

        initializeDB();

    }

    private void initializeDB() {
        if (userId != null) {
            RoomInnApplication.getInstance().getRoomsDB().initialize(userId);
        } else {
            Log.d("Firebase", "user in null");
        }
    }

}