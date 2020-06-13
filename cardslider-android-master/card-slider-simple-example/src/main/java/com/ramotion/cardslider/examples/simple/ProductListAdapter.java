package com.ramotion.cardslider.examples.simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

	private Context mContext;
	private List<Item> mItemlist;
	private LayoutInflater mInflater;

	public ProductListAdapter(Context context, List<Item> Itemlist) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mItemlist = Itemlist;
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

	@Override
	public ProductListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
	                                                            int viewType) {
		// Inflate an item view.
		View mItemView = mInflater.inflate(
				R.layout.product_list, parent, false);
		return new WordViewHolder(mItemView, this);
	}



	@Override
	public int getItemCount() {
		return mItemlist.size();
	}
}