package com.ramotion.cardslider.examples.simple;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

	private Context mContext;
	private List<Item> mItemlist;
	private LayoutInflater mInflater;
	private List<Item> purchases;
	private  final static String CHECKOUT = "CHECKOUT";
	public ProductListAdapter(Context context, List<Item> Itemlist) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mItemlist = Itemlist;
		purchases = new ArrayList<Item>();
	}

	@Override
	public void onBindViewHolder(ProductListAdapter.WordViewHolder holder, int position) {

		// Retrieve the data for that position.
		String name = mItemlist.get(position).getTitle();
		String money = mItemlist.get(position).getPrice();
		int picture = mItemlist.get(position).getDrawableId();
		// Add the data to the view holder.
		holder.title.setText(name);
		holder.price.setText(money);
		holder.food.setImageResource(picture);
	}

	class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		public final TextView title;
		public final TextView price;
		public final ImageView food;
		final ProductListAdapter mAdapter;

		public WordViewHolder(View itemView, ProductListAdapter adapter) {
			super(itemView);
			title = itemView.findViewById(R.id.title);
			price = itemView.findViewById(R.id.price);
			food = itemView.findViewById(R.id.food_img);

			this.mAdapter = adapter;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			int mPosition = getLayoutPosition();

			purchases.add( mItemlist.get(mPosition));

			String show =  mItemlist.get(mPosition).getTitle();
			String buy = view.getResources().getString(R.string.shopping_car);

			Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
					.setAction("查看購物車", new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							Intent intent= new Intent(mContext, Checkout.class);
							intent.putExtra(CHECKOUT, (Serializable) purchases);
							mContext.startActivity(intent);
						}
					});
			snackbar.setText(show+buy);
			snackbar.show();
//			View mView = snackbar.getView();
//			mView.setBackgroundColor(Color.WHITE);
//			TextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
//			tv.setTextColor(Color.BLACK);
		}
	}

	@Override
	public ProductListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
		// Inflate an item view.
		View mItemView = mInflater.inflate(
				R.layout.product_list, parent, false);
		return new WordViewHolder(mItemView, this);
	}

	@Override
	public int getItemCount() {
		return mItemlist.size();
	}

	public List<Item> getpurchases() {
		return purchases;
	}
}