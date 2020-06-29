package com.ramotion.cardslider.examples.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.WordViewHolder> {

	private final LayoutInflater mInflater;
	private List<Item> purchases;
	private  int sum = 0;
	private Context mContext;
	class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		final CheckoutListAdapter mAdapter;

		public final TextView prouct_name;
		public final TextView price;
		public final TextView count;
		public final ImageButton plus;
		public final ImageButton minus;
		public WordViewHolder(View itemView, CheckoutListAdapter adapter) {
			super(itemView);
			prouct_name = itemView.findViewById(R.id.prouct_name);
			count = itemView.findViewById(R.id.count);
			price = itemView.findViewById(R.id.price_text);
			plus  = itemView.findViewById(R.id.plus);
			minus  = itemView.findViewById(R.id.minus);
			this.mAdapter = adapter;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
		}
	}

	public CheckoutListAdapter(Context context, List<Item> Itemlist) {
		mInflater = LayoutInflater.from(context);
		this.purchases = Itemlist;
		this.mContext = context;
	}

	@Override
	public CheckoutListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
		// Inflate an item view.
		View mItemView = mInflater.inflate(
				R.layout.checkout_list, parent, false);
		return new WordViewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(final CheckoutListAdapter.WordViewHolder holder, final int position) {

		holder.plus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				purchases.get(position).plusCount();
				String price = "$" +  Integer.toString(purchases.get(position).getTotalPrice());
				holder.count.setText(Integer.toString(purchases.get(position).getCount()));
				holder.price.setText(price);
				((Checkout)mContext).SetText();

			}
		});
		holder.minus.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				purchases.get(position).minusCount();
				String price = "$" +  Integer.toString(purchases.get(position).getTotalPrice());
				holder.count.setText(Integer.toString(purchases.get(position).getCount()));
				holder.price.setText(price);
				((Checkout)mContext).SetText();

			}
		});

		String name = purchases.get(position).getTitle();
		String price = "$" +  Integer.toString(purchases.get(position).getTotalPrice());

		holder.prouct_name.setText(name);
		holder.count.setText(Integer.toString(purchases.get(position).getCount()));
		holder.price.setText(price);
	}

	@Override
	public int getItemCount() {
		return purchases.size();
	}

}
