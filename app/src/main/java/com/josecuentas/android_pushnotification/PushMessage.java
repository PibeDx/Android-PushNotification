package com.josecuentas.android_pushnotification;

import android.content.Context;

import com.josecuentas.lib_pushnotification.BasePush;
import com.josecuentas.lib_pushnotification.model.PushNotification;

/**
 * Created by jcuentas on 25/01/17.
 */

public class PushMessage extends BasePush {
    public PushMessage(Context context, PushNotification pushNotification) {
        super(context, pushNotification);
    }

    @Override protected Class<?> onGoTo() {
        return MainActivity.class;
    }

    @Override protected int getIcon() {
        return R.mipmap.ic_notification;
    }
}
