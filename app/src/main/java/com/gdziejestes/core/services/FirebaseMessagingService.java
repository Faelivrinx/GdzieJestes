package com.gdziejestes.core.services;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.gdziejestes.R;
import com.gdziejestes.ui.NotificationReceiverActivity;
import com.gdziejestes.ui.mainactivity.MainActivity;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Dominik on 06.04.2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
       // PendingIntent acceptIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent acceptIntent = new Intent(this, NotificationReceiverActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        acceptIntent.putExtra("anserw", "yes");
        acceptIntent.putExtra("message", message);
        PendingIntent pendingIntentAccept = PendingIntent.getActivity(this, 1, acceptIntent, PendingIntent.FLAG_UPDATE_CURRENT);




        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setContentTitle("Ktoś chce Cię dodać do znajomych!")
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntentAccept)
                .addAction(new android.support.v4.app.NotificationCompat.Action(R.mipmap.ic_done, "Accept", pendingIntentAccept));

       // pendingIntent = builder.addAction(new android.support.v4.app.NotificationCompat.Action(null, "Accept", muteIntent));

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
