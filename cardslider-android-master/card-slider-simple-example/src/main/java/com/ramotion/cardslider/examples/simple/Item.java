package com.ramotion.cardslider.examples.simple;

import java.io.Serializable;

public class Item implements Serializable {
    private String mTitle;
    private String mPrice;
    private int mDrawableId;

    public Item(String title, String price, int drawableId)
    {
        mTitle = title;
        mPrice = price;
        mDrawableId = drawableId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPrice() { return mPrice; }

    public int getDrawableId() { return mDrawableId; }
}
