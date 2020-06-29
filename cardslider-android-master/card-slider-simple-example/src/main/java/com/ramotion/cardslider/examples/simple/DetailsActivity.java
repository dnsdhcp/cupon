package com.ramotion.cardslider.examples.simple;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.cardslider.examples.simple.utils.DecodeBitmapTask;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity implements DecodeBitmapTask.Listener {

    static final String BUNDLE_IMAGE_ID = "BUNDLE_IMAGE_ID";

    private ImageView imageView;
    private DecodeBitmapTask decodeBitmapTask;
    private RecyclerView mRecyclerView;
    private ProductListAdapter product_Adapter;
//    private ProductListAdapter heart_Adapter;

    private List<Item> mc_Item = new ArrayList<Item>();
    private List<Item> kfc_Item = new ArrayList<Item>();
    private List<Item> heart_Item = new ArrayList<Item>();
    private List<Item> coco_Item = new ArrayList<Item>();
    private List<Item> misterdonut_Item = new ArrayList<Item>();
    private List<Item> shopping_Item = new ArrayList<Item>();
    private int bigResId ;
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
        mc_Item.add( new Item("蕈菇安格斯黑牛堡",119,R.drawable.mushroom_blackbeef,0) );
        mc_Item.add( new Item("BLT嫩煎鷄腿堡",109,R.drawable.blt_chicken,0) );
        mc_Item.add( new Item("大麥克",72,R.drawable.big_mac,0) );
        mc_Item.add( new Item("千島黃金蝦堡",69,R.drawable.shrimp_burger,0) );
        mc_Item.add( new Item("豬肉滿福堡+蛋",58,R.drawable.mcmuffin_sausage_egg,0) );
        mc_Item.add( new Item("豬肉滿福堡",48,R.drawable.mcmuffin_sausage,0) );
        mc_Item.add( new Item("無敵豬肉滿福堡+蛋",78,R.drawable.mcmuffin_twosausage_egg,0) );
        mc_Item.add( new Item("滿福堡",50,R.drawable.mcmuffin,0) );
        mc_Item.add( new Item("香鷄滿福堡+蛋",58,R.drawable.mcmuffin_chicken_egg,0) );
        mc_Item.add( new Item("香鷄滿福堡",48,R.drawable.mcmuffin_chicken,0) );
        mc_Item.add( new Item("清蔬滿福堡",58,R.drawable.mcmuffin_vegetable,0) );

        kfc_Item = new ArrayList<Item>();
        kfc_Item.add( new Item("金沙黑爵士咔啦雞腿堡",129,R.drawable.saltedegg_black_burger,1) );
        kfc_Item.add( new Item("花生熔岩咔啦雞腿堡",129,R.drawable.penut_chicken_burger,1) );
        kfc_Item.add( new Item("義式香草紙包雞",116,R.drawable.papperwrapped_chicken,1) );
        kfc_Item.add( new Item("紐奧良烤雞腿堡",109,R.drawable.roast_chicken_burger,1) );
        kfc_Item.add( new Item("墨西哥莎莎霸王捲",109,R.drawable.mexio_burrito,1) );

        heart_Item = new ArrayList<Item>();
        heart_Item.add( new Item("太妃鴛鴦奶茶",70,R.drawable.taffee_milktea,2) );
        heart_Item.add( new Item("錫蘭奶紅",50,R.drawable.ceylon_blacktea_milk,2) );
        heart_Item.add( new Item("特級奶綠",50,R.drawable.milk_greentea,2) );
        heart_Item.add( new Item("烏龍奶茶",50,R.drawable.oolong_milktea,2) );
        heart_Item.add( new Item("珍珠奶茶",50,R.drawable.pearl_milktea,2) );
        heart_Item.add( new Item("粉圓奶茶",50,R.drawable.tapiocapearl_milktea,2) );
        heart_Item.add( new Item("仙草凍奶茶",50,R.drawable.grass_blacktea,2) );
        heart_Item.add( new Item("布丁奶茶",50,R.drawable.pudding_milktea,2) );
        heart_Item.add( new Item("椰果奶茶",50,R.drawable.cocunut_milktea,2) );
        heart_Item.add( new Item("蜂蜜奶茶",50,R.drawable.honey_milktea,2) );
        heart_Item.add( new Item("暗黑水晶奶茶",60,R.drawable.blackcrystal_milktea,2) );
        heart_Item.add( new Item("黃金地瓜奶茶",75,R.drawable.sweetpotato_milktea,2) );
        heart_Item.add( new Item("芝麻奶茶",50,R.drawable.seasame_milktea,2) );

        coco_Item = new ArrayList<Item>();
        coco_Item.add( new Item("奶茶三兄弟",45,R.drawable.three_brothers_milktea,3) );
        coco_Item.add( new Item("芋頭牛奶",55,R.drawable.taro_milk,3) );
        coco_Item.add( new Item("金桔檸檬",50,R.drawable.kumquat_lemon,3) );
        coco_Item.add( new Item("芒果養樂多",55,R.drawable.mango_greentea,3) );
        coco_Item.add( new Item("拿鐵咖啡",60,R.drawable.latte_coffee,3) );

        misterdonut_Item = new ArrayList<Item>();
        misterdonut_Item.add( new Item("精靈求波堤",55,R.drawable.pokeball_ponde,4) );
        misterdonut_Item.add( new Item("小蜜蜂波堤",45,R.drawable.bee_ponde,4) );
        misterdonut_Item.add( new Item("辻利抹茶芝麻波堤",116,R.drawable.matcha_seasame_ponde,4) );
        misterdonut_Item.add( new Item("辻利抹茶餅乾巧貝",109,R.drawable.matcha_cookie_cream,4) );
        misterdonut_Item.add( new Item("起司火腿派",50,R.drawable.cheese_ham_pie,4) );
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

        switch (smallResId) {
            case R.drawable.mc_small_logo:
                bigResId = R.drawable.mcdonalds_logo;
                product_Adapter = new ProductListAdapter(this, mc_Item);
                break;
            case R.drawable.kfc_small_logo:
                bigResId = R.drawable.kfc_logo;
                product_Adapter = new ProductListAdapter(this, kfc_Item);
                break;
            case R.drawable.heart_small_logo:
                bigResId = R.drawable.heart_logo;
                product_Adapter = new ProductListAdapter(this, heart_Item);
                break;
            case R.drawable.coco_small_logo:
                bigResId = R.drawable.coco_logo;
                product_Adapter = new ProductListAdapter(this, coco_Item);
                break;
            case R.drawable.misterdonut_small_logo:
                bigResId = R.drawable.misterdonut_small_logo;
                product_Adapter = new ProductListAdapter(this, misterdonut_Item);
                break;
            default: bigResId = R.drawable.mc_small_logo;
        }

        mRecyclerView.setAdapter(product_Adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL);

        mRecyclerView.addItemDecoration(mDividerItemDecoration);

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
