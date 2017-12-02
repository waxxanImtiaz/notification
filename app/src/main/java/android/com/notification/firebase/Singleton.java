package android.com.notification.firebase;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wassan on 11/12/2017.
 */

public class Singleton {
    private static Singleton singleton;
    private Context context;
    private RequestQueue requestQueue;

    private Singleton(Context context) {
        this.context = context;

        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }

        return requestQueue;

    }


    public static synchronized Singleton getInstance(Context context) {

        if (singleton == null) {
            return new Singleton(context);
        }
        return singleton;

    }

    public<T> void addToRequestQue(Request<T> req){

        getRequestQueue().add(req);

    }
}

