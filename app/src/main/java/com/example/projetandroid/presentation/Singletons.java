package com.example.projetandroid.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import com.example.projetandroid.Constant;
import com.example.projetandroid.R;
import com.example.projetandroid.data.GerritAPI;
import com.example.projetandroid.presentation.model.MyFirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
    private static Gson gsonInstance;
    private static GerritAPI gerritAPIInstance;
    private static SharedPreferences sharedPreferencesInstance;
    private static NotificationCompat.Builder notificationBuilder;

    public static NotificationCompat.Builder getNotificationBuilder(MyFirebaseMessagingService myFirebaseMessagingService, RemoteMessage remoteMessage) {
        if (notificationBuilder == null) {

            return new NotificationCompat.Builder(myFirebaseMessagingService, Constant.CHANNEL_ID)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);

        }
        return notificationBuilder;
    }

    public static GerritAPI getgerritAPIInstance() {
        if (gerritAPIInstance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGsonInstance()))
                    .build();
            return retrofit.create(GerritAPI.class);

        }
        return gerritAPIInstance;
    }

    public static Gson getGsonInstance() {
        if (gsonInstance == null) {
            return new GsonBuilder()
                    .setLenient()
                    .create();

        }
        return gsonInstance;
    }

    public static SharedPreferences getSharedPreferencesInstance(Context view) {
        if (sharedPreferencesInstance == null) {
            return view.getSharedPreferences(Constant.FACT_PREF, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
