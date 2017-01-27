package com.josecuentas.android_pushnotification.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.josecuentas.android_pushnotification.PushFactory;
import com.josecuentas.lib_pushnotification.FactoryCallback;
import com.josecuentas.lib_pushnotification.model.PushNotification;

/**
 * Created by jcuentas on 25/01/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private RemoteMessage mRemoteMessage;

    @Override public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "onMessageReceived() called with: remoteMessage = [" + remoteMessage + "]");
        mRemoteMessage = remoteMessage;
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title = notification.getTitle();
        String body = notification.getBody();
        PushNotification pushNotification = new PushNotification(mRemoteMessage.getData());
        pushNotification.setTitle(title);
        pushNotification.setBody(body);
        FactoryCallback factoryCallback = new PushFactory(this, pushNotification);
        factoryCallback
                .createPush()
                .showNotification();
    }

}
