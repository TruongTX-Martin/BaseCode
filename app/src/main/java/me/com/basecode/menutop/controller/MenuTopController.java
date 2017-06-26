package me.com.basecode.menutop.controller;

import android.view.View;

import me.com.basecode.base.controller.BaseController;
import me.com.basecode.menutop.delegate.MenuTopDelegate;
import me.com.basecode.slidemenu.fragment.SlideMenuFragment;

public class MenuTopController extends BaseController {

    protected SlideMenuFragment slideMenuFragment;
    protected MenuTopDelegate delegate;

    public void setSlideMenu(SlideMenuFragment mNavigationDrawerFragment) {
        this.slideMenuFragment = mNavigationDrawerFragment;
    }

    public SlideMenuFragment getSlideMenu() {
        return slideMenuFragment;
    }

    public void setMenuTopDelegate(MenuTopDelegate mMenuTopDelegate) {
        this.delegate = mMenuTopDelegate;
    }


    @Override
    public void onStart() {
        delegate.getImageViewMenu().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slideMenuFragment.checkNavigation()){
                    slideMenuFragment.closeSlideMenu();
                }else{
                    slideMenuFragment.openMenu();
                }
            }
        });
    }

}
