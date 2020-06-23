package com.ramotion.cardslider.examples.simple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Checkout extends AppCompatActivity {

	private TextView tip_text;
	private TextView service_fee_text;
	private TextView sum_text;

	private ImageButton plus;
	private ImageButton minus;
	private Button finish;
	private List<Item> purchases;

	private CheckoutListAdapter checkoutListAdapter;
	private  final static String CHECKOUT = "CHECKOUT";

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

		purchases = new ArrayList<Item>();
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();

		purchases = (List<Item>) intent.getSerializableExtra(CHECKOUT);
		checkoutListAdapter = new CheckoutListAdapter(this, purchases);

		RecyclerView mRecyclerView = findViewById(R.id.checkout_recycleview);;
		mRecyclerView.setAdapter(checkoutListAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);

		mRecyclerView.addItemDecoration(mDividerItemDecoration);
	}

}
