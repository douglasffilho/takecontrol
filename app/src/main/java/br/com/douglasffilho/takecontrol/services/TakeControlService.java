package br.com.douglasffilho.takecontrol.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import br.com.douglasffilho.takecontrol.App;
import br.com.douglasffilho.takecontrol.MainActivity;
import br.com.douglasffilho.takecontrol.R;

public class TakeControlService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent notificationArrivalIntent = new Intent(this, MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationArrivalIntent, 0);

        final Notification persistentNotification = new NotificationCompat
                .Builder(this, App.NOTIFICATION_CHANNEL_ID)
                .setContentTitle("Take Control Service")
                .setContentText("Keep this running")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        this.startForeground(1, persistentNotification);

        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
