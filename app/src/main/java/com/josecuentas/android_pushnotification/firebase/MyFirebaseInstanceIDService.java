package com.josecuentas.android_pushnotification.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jcuentas on 25/01/17.
 */

public class MyFirebaseInstanceIDService  extends FirebaseInstanceIdService{
    public static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh() called: [refreshedToken]: " + refreshedToken);
    }
}
