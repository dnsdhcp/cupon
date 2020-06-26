package com.ramotion.cardslider.examples.simple;

import java.io.Serializable;

public class Item implements Serializable {
    private String mTitle;
    private int mPrice;
    private int mDrawableId;
    private int Count;

    public Item(String title, int price, int drawableId)
    {
        mTitle = title;
        mPrice = price;
        mDrawableId = drawableId;
        Count = 1;
    }

    public void setCount(int n){Count = n;}

    public void plusCount(){Count ++;}

    public void minusCount(){ if(Count>1) Count --; }

    public int getTotalPrice(){return Count * mPrice;}

    public int getCount(){return Count;}

    public String getTitle() {
        return mTitle;
    }

    public int getPrice() { return mPrice; }

    public int getDrawableId() { return mDrawableId; }
}
