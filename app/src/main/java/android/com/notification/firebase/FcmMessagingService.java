package android.com.notification.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.com.notification.R;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by wassan on 11/12/2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();


        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("myMessage","message="+message);
    //    Toast.makeText(this, "message="+message, Toast.LENGTH_SHORT).show();
//         Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {


            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

        }

        Intent intent = new Intent(this, FirebaseMessagingService.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(message).setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pIntent)
                .addAction(R.drawable.ic_launcher_background, "Call", pIntent)
              .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
        super.onMessageReceived(remoteMessage);
    }
}
