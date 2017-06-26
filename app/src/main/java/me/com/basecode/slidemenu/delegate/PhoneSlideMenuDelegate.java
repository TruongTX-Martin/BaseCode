package me.com.basecode.slidemenu.delegate;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.com.basecode.base.delegate.BaseDelegate;
import me.com.basecode.slidemenu.entity.ItemNavigation;

/**
 * Created by truongtechno on 26/06/2017.
 */

public interface PhoneSlideMenuDelegate  extends BaseDelegate{
    List<ItemNavigation> getListItemNavigation();
    RecyclerView getRecycleView();
}
