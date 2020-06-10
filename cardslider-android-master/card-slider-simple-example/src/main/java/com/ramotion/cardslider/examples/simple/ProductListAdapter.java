package com.ramotion.cardslider.examples.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ProductListAdapter extends
		RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

	private final LinkedList<String> titlelist;
	private final LinkedList<String> pricelist;
	private final LayoutInflater mInflater;

	class WordViewHolder extends RecyclerView.ViewHolder
			implements View.OnClickListener {
		public final TextView title;
		public final TextView price;
		final ProductListAdapter mAdapter;

		public WordViewHolder(View itemView, ProductListAdapter adapter) {
			super(itemView);
			title = itemView.findViewById(R.id.title);
			price = itemView.findViewById(R.id.price);

			this.mAdapter = adapter;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			// Get the position of the item that was clicked.
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

	public ProductListAdapter(Context context, LinkedList<String> titlelist, LinkedList<String> pricelist) {
		mInflater = LayoutInflater.from(context);
		this.titlelist = titlelist;
		this.pricelist = pricelist;
	}

	@Override
	public ProductListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
	                                                            int viewType) {
		// Inflate an item view.
		View mItemView = mInflater.inflate(
				R.layout.product_list, parent, false);
		return new WordViewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(ProductListAdapter.WordViewHolder holder,
	                             int position) {
		// Retrieve the data for that position.
		String name = titlelist.get(position);
		String money = pricelist.get(position);
		// Add the data to the view holder.
		holder.title.setText(name);
		holder.price.setText(money);
	}

	@Override
	public int getItemCount() {
		return titlelist.size();
	}
}