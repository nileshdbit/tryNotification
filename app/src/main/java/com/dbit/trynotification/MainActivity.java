package com.dbit.trynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String context="This is the notification example. Also the notification has a big message to display";
    int NOTIFICATION_ID = 7757;
    String CHANNEL_ID;


    NotificationManager notificationManager;
    PendingIntent resultPendingIntent,settingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showNotification(View v) {

        notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        switch(v.getId()){
            case R.id.bt_normalv :
                showNormalTextViewNotification();
                break;
            case R.id.bt_bigtxt :
                showBigTextViewNotification();
                break;
            case R.id.bt_bigpic :
                showBigPicNotification();
                break;
            case R.id.bt_inbox :
                showBigInboxNotification();
                break;
        }
    }

    private void setNotificationChannel(){
        CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            notificationManager.createNotificationChannel(mChannel);
        }
    }


    private void setActions(){

        Intent resultIntent = new Intent(MainActivity.this, notif.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(notif.class);
        stackBuilder.addNextIntent(resultIntent);
        resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent j = new Intent(MainActivity.this, setting.class);
        TaskStackBuilder stackBuilder_setting = TaskStackBuilder.create(this);
        stackBuilder_setting.addParentStack(setting.class);
        stackBuilder_setting.addNextIntent(j);
        settingIntent = stackBuilder_setting.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

    }
    private void showBigInboxNotification() {

        // Step 1: Set Notification Channel

        setNotificationChannel();

        // Step 2 : Set the intent and Action buttons
        setActions();

        // Step 3: Build the content of Notification and pass to notification manager
/// new as compared to previous
        NotificationCompat.InboxStyle style=new NotificationCompat.InboxStyle();
        style.setBigContentTitle("Inbox Style Notificaiton");
        style.addLine("Email one");
        style.addLine("Email two");
        style.addLine("Email three");
        style.addLine("Email four");
        style.addLine("Email five");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_help);
        builder.setContentTitle("DBIT");
        builder.setContentText(context);
        builder.setTicker("Hi this is ticker");
        builder.setAutoCancel(true);

        builder.addAction(R.drawable.ic_setting, "Setting", settingIntent);

/// new as compared to previous
        builder.setStyle(style);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());


    }

    private void showBigPicNotification() {

        // Step 1: Set Notification Channel

        setNotificationChannel();

        // Step 2 : Set the intent and Action buttons
        setActions();
        // Step 3: Build the content of Notification and pass to notification manager
/// new as compared to previous
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(),R.drawable.exam);
        NotificationCompat.BigPictureStyle style=new NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("Big Picture Regular Notificaiton");
        style.bigPicture(bmp);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_help);
        builder.setContentTitle("title");
        builder.setContentText(context);
        builder.setTicker("Hi this is ticker");
        builder.setAutoCancel(true);

        builder.addAction(R.drawable.ic_setting, "Setting", settingIntent);

/// new as compared to previous
        builder.setStyle(style);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }


    public void showNormalTextViewNotification()
    {

        // Step 1: Set Notification Channel

        setNotificationChannel();

        // Step 2 : Set the intent and Action buttons
        setActions();

        // Step 3: Build the content of Notification and pass to notification manager


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_help);
        builder.setContentTitle("title");
        builder.setContentText(context);
        builder.setTicker("Hi this is ticker");
        builder.setAutoCancel(true);
        builder.addAction(R.drawable.ic_setting, "Setting", settingIntent);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());



    }

    private void showBigTextViewNotification() {

        // Step 1: Set Notification Channel

        setNotificationChannel();

        // Step 2 : Set the intent and Action buttons
        setActions();

        // Step 3: Build the content of Notification and pass to notification manager
/// new as compared to previous
        NotificationCompat.BigTextStyle style=new NotificationCompat.BigTextStyle();
        style.setBigContentTitle("BigText Regular Notificaiton");
        style.bigText(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_help);
        builder.setContentTitle("title");
        builder.setContentText(context);
        builder.setTicker("Hi this is ticker");
        builder.setAutoCancel(true);

        builder.addAction(R.drawable.ic_setting, "Setting", settingIntent);

/// new as compared to previous
        builder.setStyle(style);

        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }


}
