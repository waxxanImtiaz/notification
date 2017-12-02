package android.com.notification;

import android.com.notification.firebase.Singleton;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public static final String serverLink = "http://serveasy.pk/andriod_services/";
    public static final String userLink = serverLink.concat("notifications.php");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.send)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToken();
            }
        });
    }

    private void sendToken(){
        try {
            final String token = getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE).getString(getString(R.string.FCM_TOKEN),"");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, userLink
                    , new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, "Data stored", Toast.LENGTH_SHORT).show();
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                 //   Toast.makeText(MainActivity.this, "Data not stored", Toast.LENGTH_SHORT).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fcm_token", token);
                    //params.put("firebase_token","true");
                    Log.d("fcm_token","token="+token);
                    return params;
                }
            };

            Singleton.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
        }catch (Exception e){

        }
    }
}
