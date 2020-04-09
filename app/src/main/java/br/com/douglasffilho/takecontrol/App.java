package br.com.douglasffilho.takecontrol;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;

import br.com.douglasffilho.takecontrol.services.AccessibilityHandleService;
import br.com.douglasffilho.takecontrol.services.TakeControlService;

public class App extends Application {
    public static final String NOTIFICATION_CHANNEL_ID = "TakeControlServiceChannel";
    private Intent takeControlService;
    private Intent accessibilityHandleService;

    @Override
    public void onCreate() {
        super.onCreate();

        startNotificationChannel();
        startServices();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        stopServices();
        stopNotificationChannel();
    }

    private void startNotificationChannel() {
        final NotificationChannel notificationChannel = new NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_ID,
                NotificationManager.IMPORTANCE_DEFAULT
        );

        final NotificationManager notificationManager = this.getSystemService(NotificationManager.class);

        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void stopNotificationChannel() {
        final NotificationManager notificationManager = this.getSystemService(NotificationManager.class);

        if (notificationManager != null) {
            notificationManager.deleteNotificationChannel(NOTIFICATION_CHANNEL_ID);
        }
    }

    private void startServices() {
        takeControlService = new Intent(this, TakeControlService.class);
        this.startService(takeControlService);

        accessibilityHandleService = new Intent(this, AccessibilityHandleService.class);
        this.startService(accessibilityHandleService);
    }

    private void stopServices() {
        this.stopService(takeControlService);
        this.stopService(accessibilityHandleService);
    }

}
