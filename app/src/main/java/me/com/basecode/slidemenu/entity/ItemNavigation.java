package me.com.basecode.slidemenu.entity;


import android.graphics.drawable.Drawable;

import me.com.basecode.base.entity.BaseEntity;


public class ItemNavigation extends BaseEntity {

    protected String mName;
    protected Drawable mDrawble;
    protected String mUrl;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }


    public void setDrawble(Drawable mDrawble) {
        this.mDrawble = mDrawble;
    }

    public Drawable getDrawble() {
        return mDrawble;
    }
}
