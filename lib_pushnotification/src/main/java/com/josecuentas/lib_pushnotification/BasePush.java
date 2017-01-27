package com.josecuentas.lib_pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.NotificationCompat;

import com.josecuentas.lib_pushnotification.model.PushNotification;

/**
 * Created by jcuentas on 25/01/17.
 */

public abstract class BasePush implements Push {

    public static final String BUNDLE_BODY = "data";
    public static final String BUNDLE_TYPE = "type";

    protected String mTitle;
    protected String mBody;
    protected NotificationCompat.Builder nBuilder;
    protected final Context mContext;
    protected final PushNotification mPushNotification;

    protected abstract @DrawableRes int getIcon();
    protected abstract Class<?> onGoTo();

    public BasePush(Context context, PushNotification pushNotification) {
        mContext = context;
        mPushNotification = pushNotification;

        mTitle = mPushNotification.getTitle();
        mBody = mPushNotification.getBody();
    }

    protected Intent getIntentGo() {
        Intent intent = new Intent(mContext, onGoTo());
        intent.putExtras(getBundle());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    protected Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_BODY, getData());
        bundle.putString(BUNDLE_TYPE, getType());
        return bundle;
    }

    @Override public void showNotification() {
        validation();
        NotificationManager notificationManager = getNotificationManager();
        notificationManager.notify(0, nBuilder.build());
    }

    protected void notification() {
        Bitmap bitmapLargeIcon = BitmapFactory.decodeResource(getResources(), getIcon());
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, getIntentGo(), PendingIntent.FLAG_ONE_SHOT);

        nBuilder = new NotificationCompat.Builder(getContext());
        nBuilder.setSmallIcon(getIcon())
                .setLargeIcon(bitmapLargeIcon)
                .setContentTitle(mTitle)
                .setContentText(mBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void validation() {
        if (mTitle == null || mTitle.isEmpty()) throw new NullPointerException("title is empty");
        if (mBody == null || mBody.isEmpty()) throw new NullPointerException("body is empty");
    }

    private Object getSystemService(String name) {
        return mContext.getSystemService(name);
    }

    private Resources getResources() {
        return mContext.getResources();
    }

    private Context getContext() {
        return mContext;
    }

    protected String getData() {
        return mPushNotification.getData();
    }

    protected String getType() {
        return mPushNotification.getType();
    }
}
