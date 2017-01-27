package com.josecuentas.android_pushnotification;

import android.content.Context;

import com.josecuentas.lib_pushnotification.FactoryCallback;
import com.josecuentas.lib_pushnotification.Push;
import com.josecuentas.lib_pushnotification.model.PushNotification;

/**
 * Created by jcuentas on 25/01/17.
 */

public class PushFactory implements FactoryCallback {
    public static final String TAG = PushFactory.class.getSimpleName();

    public static final String TYPE_MESSAGE = "1";
    private Push mPush;
    private final Context mContext;
    private final PushNotification mPushNotification;

    public PushFactory(Context context, PushNotification pushNotification) {
        mContext = context;
        mPushNotification = pushNotification;
    }


    @Override public Push createPush() {
        String type = mPushNotification.getType();
        switch (type ) {
            case TYPE_MESSAGE: return new PushMessage(mContext, mPushNotification);
        }
        return mPush;
    }

}
