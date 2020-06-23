package com.ramotion.cardslider.examples.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.WordViewHolder> {

	private final LayoutInflater mInflater;
	private List<Item> purchases;
	private  int sum = 0;
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
//			// Get the position of the item that was clicked.
//			int mPosition = getLayoutPosition();
//
//			// Use that to access the affected item in mWordList.
//			String element = mWordList.get(mPosition);
//			// Change the word in the mWordList.
//
//			mWordList.set(mPosition, "Clicked! " + element);
//			// Notify the adapter, that the data has changed so it can
//			// update the RecyclerView to display the data.
//			mAdapter.notifyDataSetChanged();
		}
	}

	public CheckoutListAdapter(Context context, List<Item> Itemlist) {
		mInflater = LayoutInflater.from(context);
		this.purchases = Itemlist;
	}

	@Override
	public CheckoutListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
		// Inflate an item view.
		View mItemView = mInflater.inflate(
				R.layout.checkout_list, parent, false);
		return new WordViewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(CheckoutListAdapter.WordViewHolder holder,int position) {

		int count = 1;
		String name = purchases.get(position).getTitle();
		String price = purchases.get(position).getPrice();
		holder.prouct_name.setText(name);
		holder.count.setText(Integer.toString(count));
		holder.price.setText(price );
//		holder.plus.setOnClickListener(clickListener);
//		holder.minus.setOnClickListener(clickListener);
	}

	View.OnClickListener clickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.plus) {
				sum++;
			}
			else if (v.getId() == R.id.minus) {
				sum--;
			}
		}
	};
	@Override
	public int getItemCount() {
		return purchases.size();
	}
}