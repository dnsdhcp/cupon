package com.ramotion.cardslider.examples.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Checkout extends AppCompatActivity{

	private TextView tip_text;
	private TextView service_fee_text;
	private TextView sum_text;

	private ImageButton plus;
	private ImageButton minus;
	private Button finish;
	private List<Item> purchases;
	private boolean IsPickTime;
	private CheckoutListAdapter checkoutListAdapter;
	private  final static String CHECKOUT = "CHECKOUT";
	private int pick_hour;
	private int pick_minute;

	private static final int NOTIFICATION_ID = 0;
	private static final String PRIMARY_CHANNEL_ID ="primary_notification_channel";
	private NotificationManager mNotificationManager;

	private FirebaseFirestore db;
	private String order_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkout);
		tip_text = findViewById(R.id.tip_text);
		service_fee_text = findViewById(R.id.service_fee_text);
		sum_text = findViewById(R.id.sum_text);
		finish = findViewById(R.id.finish);
		plus = findViewById(R.id.plus);
		minus = findViewById(R.id.minus);

		IsPickTime = false;

		order_num = getResources().getString(R.string.ordernum);

		db = FirebaseFirestore.getInstance();
		purchases = new ArrayList<Item>();
		Intent intent = this.getIntent();

		purchases = (List<Item>) intent.getSerializableExtra(CHECKOUT);
		checkoutListAdapter = new CheckoutListAdapter(this, purchases);

		RecyclerView mRecyclerView = findViewById(R.id.checkout_recycleview);;
		mRecyclerView.setAdapter(checkoutListAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);

		mRecyclerView.addItemDecoration(mDividerItemDecoration);

		SetText();
	}

	public  void SetText(){
		String total_price = Integer.toString(calc_total_price());
		tip_text.setText(getResources().getString(R.string.tip) +total_price);
		service_fee_text.setText(getResources().getString(R.string.service_fee) + 0 );
		sum_text.setText( getResources().getString(R.string.sum) + total_price);
	}

	public int calc_total_price(){
		int sum = 0;
		for(int i=0;i<purchases.size();i++){
			sum += purchases.get(i).getTotalPrice();
		}
		return sum;
	}

	public void picktime(View view) {
		IsPickTime = true;
		Calendar mcurrentTime = Calendar.getInstance();
		final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
		int minute = mcurrentTime.get(Calendar.MINUTE);

		TimePickerDialog mTimePicker;
		mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
				 pick_hour = selectedHour;
				 pick_minute = selectedMinute;

				AlertDialog.Builder dialog = new AlertDialog.Builder(Checkout.this);

				String title  = "系統將會在 " + selectedHour + ":" ;
				if (selectedMinute < 10 ){
					title+= "0" + selectedMinute + " 提醒您取餐!";
				}
				else {
					title+= selectedMinute + " 提醒您取餐!";
				}
				dialog.setTitle(title);
				dialog.setNegativeButton("OK",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
				dialog.show();

				CreateNotification();
			}
		}, hour, minute, false);//Yes 24 hour time
		mTimePicker.show();
	}

	public int CreateNum(){
		int min = 0000;
		int max = 2000;
		int random = new Random().nextInt((max - min) + 1) + min;
		return random;
	}
	public void CreateNotification(){
		order_num += Integer.toString(CreateNum());

		mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

		Intent notifyIntent = new Intent(getApplicationContext(), AlarmReceiver.class);

		notifyIntent.putExtra("extra",order_num);

		final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
				(getApplicationContext(), NOTIFICATION_ID, notifyIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);

		final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,pick_hour);
		calendar.set(Calendar.MINUTE,pick_minute);
		calendar.set(Calendar.SECOND,0);
		if (alarmManager != null) {
			alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),notifyPendingIntent);
		}
	}


	public void SendOrder(final View view) {
		if(!IsPickTime){
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setTitle(getResources().getString(R.string.alert_title));
			dialog.setNegativeButton(getResources().getString(R.string.pick_time),new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					picktime(view);
				}
			});
			dialog.show();
		}

		for(int i=0;i<purchases.size();i++){
			db.collection("order").add(purchases.get(i));
		}
	}

}
