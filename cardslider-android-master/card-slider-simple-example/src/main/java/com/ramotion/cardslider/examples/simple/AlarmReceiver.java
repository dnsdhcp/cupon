package com.ramotion.cardslider.examples.simple;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

	private NotificationManager mNotificationManager;
	// Notification ID.
	private static final int NOTIFICATION_ID = 0;
	// Notification channel ID.
	private static final String PRIMARY_CHANNEL_ID =
			"primary_notification_channel";
	private String num;
	/**
	 * Called when the BroadcastReceiver receives an Intent broadcast.
	 *
	 * @param context The Context in which the receiver is running.
	 * @param intent The Intent being received.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		num = intent.getStringExtra("extra");
		deliverNotification(context);
	}

	/**
	 * Builds and delivers the notification.
	 *
	 * @param context, activity context.
	 */
	private void deliverNotification(Context context) {
		Intent contentIntent = new Intent(context, MainActivity.class);
		PendingIntent contentPendingIntent = PendingIntent.getActivity
				(context, NOTIFICATION_ID, contentIntent, PendingIntent
						.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder builder = new NotificationCompat.Builder
				(context, PRIMARY_CHANNEL_ID)
				.setSmallIcon(R.drawable.ic_order)
				.setContentTitle(context.getString(R.string.order_title))
				.setContentText(context.getString(R.string.order_content) + num)
				.setContentIntent(contentPendingIntent)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setAutoCancel(true)
				.setDefaults(NotificationCompat.DEFAULT_ALL);
		mNotificationManager.notify(NOTIFICATION_ID, builder.build());
	}
}
