package android.com.notification.firebase;

import android.com.notification.MainActivity;
import android.com.notification.R;
import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by wassan on 11/12/2017.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();



        Log.d("myToken","token="+refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //Store token into shared preferences
        getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE).edit().putString(getString(R.string
        .FCM_TOKEN),refreshedToken).commit();
      //  sendRegistrationToServer(refreshedToken);
    }
}
