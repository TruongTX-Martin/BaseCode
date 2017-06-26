package me.com.basecode.slidemenu.controller;

import android.view.View;

import me.com.basecode.R;
import me.com.basecode.base.controller.BaseController;
import me.com.basecode.base.fragment.BaseFragment;
import me.com.basecode.base.manager.BaseManager;
import me.com.basecode.common.listener.RecyclerTouchListener;
import me.com.basecode.common.utils.ClientUtil;
import me.com.basecode.config.Rconfig;
import me.com.basecode.fragment.HomeFragment;
import me.com.basecode.fragment.PersonalFragment;
import me.com.basecode.slidemenu.delegate.CloseSlideMenuDelegate;
import me.com.basecode.slidemenu.delegate.PhoneSlideMenuDelegate;
import me.com.basecode.slidemenu.entity.ItemNavigation;

/**
 * Created by truongtechno on 26/06/2017.
 */

public class PhoneSlideMenuController extends BaseController {

    private PhoneSlideMenuDelegate delegate;
    private CloseSlideMenuDelegate closeSlideMenuDelegate;

    public void setDelegate(PhoneSlideMenuDelegate delegate) {
        this.delegate = delegate;
    }


    public void setCloseSlideMenuDelegate(CloseSlideMenuDelegate closeSlideMenuDelegate) {
        this.closeSlideMenuDelegate = closeSlideMenuDelegate;
    }

    @Override
    public void onStart() {

        delegate.getRecycleView().addOnItemTouchListener(new RecyclerTouchListener(BaseManager.getIntance().getCurrentContext(), delegate.getRecycleView(), new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ItemNavigation itemNavigation = delegate.getListItemNavigation().get(position);
                handleClickItem(itemNavigation);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private void handleClickItem(ItemNavigation item){
        if(item == null) return;
        BaseFragment fragment = null;
        if(item.getName().equals(Rconfig.getInstance().getStringLanguage(R.string.home))) {
            fragment = HomeFragment.newInstance();
        }else if(item.getName().equals(Rconfig.getInstance().getStringLanguage(R.string.personal))){
            fragment = PersonalFragment.newIntance();
        }

        if(fragment != null) {
            BaseManager.getIntance().replaceFragment(fragment);
        }

        closeSlideMenuDelegate.closeSlideMenu();



    }



}
