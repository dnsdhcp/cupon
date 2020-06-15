package com.ramotion.cardslider.examples.simple;

import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ramotion.cardslider.examples.simple.utils.DecodeBitmapTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements DecodeBitmapTask.Listener {

    static final String BUNDLE_IMAGE_ID = "BUNDLE_IMAGE_ID";

    private ImageView imageView;
    private DecodeBitmapTask decodeBitmapTask;
    private RecyclerView mRecyclerView;
    private ProductListAdapter mc_Adapter;
    private ProductListAdapter heart_Adapter;

    private List<Item> mc_Item = new ArrayList<Item>();
    private List<Item> heart_Item = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final int smallResId = getIntent().getIntExtra(BUNDLE_IMAGE_ID, -1);
        if (smallResId == -1) {
            finish();
            return;
        }

        initItem();

        mRecyclerView = findViewById(R.id.recyclerview);

        mc_Adapter = new ProductListAdapter(this, mc_Item);
        mRecyclerView.setAdapter(mc_Adapter);

        heart_Adapter = new ProductListAdapter(this, heart_Item);
        mRecyclerView.setAdapter(heart_Adapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);

        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        imageView = (ImageView)findViewById(R.id.image);
        imageView.setImageResource(smallResId);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsActivity.super.onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            loadFullSizeBitmap(smallResId);
        } else {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {

                private boolean isClosing = false;

                @Override public void onTransitionPause(Transition transition) {}
                @Override public void onTransitionResume(Transition transition) {}
                @Override public void onTransitionCancel(Transition transition) {}

                @Override public void onTransitionStart(Transition transition) {
                    if (isClosing) {
                        addCardCorners();
                    }
                }

                @Override public void onTransitionEnd(Transition transition) {
                    if (!isClosing) {
                        isClosing = true;

                        removeCardCorners();
                        loadFullSizeBitmap(smallResId);
                    }
                }
            });
        }

    }
    private  void initItem(){

        mc_Item = new ArrayList<Item>();

        mc_Item.add( new Item("豬肉滿福堡+蛋","$58",R.drawable.mcmuffin_sausage_egg) );
        mc_Item.add( new Item("豬肉滿福堡","$48",R.drawable.mcmuffin_sausage) );
        mc_Item.add( new Item("無敵豬肉滿福堡+蛋","$78",R.drawable.mcmuffin_twosausage_egg) );
        mc_Item.add( new Item("滿福堡","$50",R.drawable.mcmuffin) );
        mc_Item.add( new Item("香鷄滿福堡+蛋","$58",R.drawable.mcmuffin_chicken_egg) );
        mc_Item.add( new Item("香鷄滿福堡","$48",R.drawable.mcmuffin_chicken) );
        mc_Item.add( new Item("清蔬滿福堡","$58",R.drawable.mcmuffin_vegetable) );


        heart_Item = new ArrayList<Item>();

        heart_Item.add( new Item("太妃鴛鴦奶茶","M:$60  L:70",R.drawable.taffee_milktea) );
        heart_Item.add( new Item("錫蘭奶紅","M:$40  L:50",R.drawable.ceylon_blacktea_milk) );
        heart_Item.add( new Item("特級奶綠","M:$40  L:50",R.drawable.milk_greentea) );
        heart_Item.add( new Item("烏龍奶茶","M:$40  L:50",R.drawable.oolong_milktea) );
        heart_Item.add( new Item("珍珠奶茶","M:$40  L:50",R.drawable.pearl_milktea) );
        heart_Item.add( new Item("粉圓奶茶","M:$40   L:50",R.drawable.tapiocapearl_milktea) );
        heart_Item.add( new Item("仙草凍奶茶","M:$40  L:50",R.drawable.grass_blacktea) );
        heart_Item.add( new Item("布丁奶茶","M:$40  L:50",R.drawable.pudding_milktea) );
        heart_Item.add( new Item("椰果奶茶","M:$40  L:50",R.drawable.cocunut_milktea) );
        heart_Item.add( new Item("蜂蜜奶茶","M:$45  L:60",R.drawable.honey_milktea) );
        heart_Item.add( new Item("暗黑水晶奶茶","M:$45  L:60",R.drawable.blackcrystal_milktea) );
        heart_Item.add( new Item("黃金地瓜奶茶","L:75",R.drawable.sweetpotato_milktea) );
        heart_Item.add( new Item("芝麻奶茶","M:$40  L:50",R.drawable.seasame_milktea) );

    }
    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing() && decodeBitmapTask != null) {
            decodeBitmapTask.cancel(true);
        }
    }

    private void addCardCorners() {
        final CardView cardView = (CardView) findViewById(R.id.card);
        cardView.setRadius(25f);
    }

    private void removeCardCorners() {
        final CardView cardView = (CardView)findViewById(R.id.card);
        ObjectAnimator.ofFloat(cardView, "radius", 0f).setDuration(50).start();
    }

    private void loadFullSizeBitmap(int smallResId) {
        int bigResId;
        switch (smallResId) {
            case R.drawable.mcdonalds_logo: bigResId = R.drawable.mcdonalds_logo; break;
            case R.drawable.heart_logo: bigResId = R.drawable.heart_logo; break;
            case R.drawable.p3: bigResId = R.drawable.p3_big; break;
            case R.drawable.p4: bigResId = R.drawable.p4_big; break;
            case R.drawable.p5: bigResId = R.drawable.p5_big; break;
            default: bigResId = R.drawable.heart_logo;
        }

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        final int w = metrics.widthPixels;
        final int h = metrics.heightPixels;

        decodeBitmapTask = new DecodeBitmapTask(getResources(), bigResId, w, h, this);
        decodeBitmapTask.execute();
    }

    @Override
    public void onPostExecuted(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

}
