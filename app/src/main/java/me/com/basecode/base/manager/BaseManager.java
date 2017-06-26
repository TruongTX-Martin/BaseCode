package me.com.basecode.base.manager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import me.com.basecode.base.fragment.BaseFragment;
import me.com.basecode.common.utils.ClientUtil;
import me.com.basecode.config.Rconfig;
import me.com.basecode.menutop.controller.MenuTopController;
import me.com.basecode.slidemenu.controller.PhoneSlideMenuController;

public class BaseManager {
    private PhoneSlideMenuController mSlideMenuController;
    protected MenuTopController mMenuTopController;
    private Activity mCurrentActivity;
    private static BaseManager instance;
    private Context mCurrentContext;
    private FragmentManager mManager;
    private DrawerLayout drawerLayout;
    private LinearLayout layoutRoot;

    public Context getCurrentContext() {
        return mCurrentContext;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }


    public void setLayoutRoot(LinearLayout layoutRoot) {
        this.layoutRoot = layoutRoot;
    }

    public LinearLayout getLayoutRoot() {
        return layoutRoot;
    }




    public void setCurrentActivity(Activity mCurrentActivity) {
        this.mCurrentActivity = mCurrentActivity;
    }



    public void setCurrentContext(Context mCurrentContext) {
        this.mCurrentContext = mCurrentContext;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }


    public FragmentManager getManager() {
        return mManager;
    }

    public void setManager(FragmentManager mManager) {
        this.mManager = mManager;
    }

    public static BaseManager getIntance() {
        if (null == instance) {
            instance = new BaseManager();
        }
        return instance;
    }

    public void setSlideMenuController(PhoneSlideMenuController controller) {
        mSlideMenuController = controller;
    }

    public PhoneSlideMenuController getSlideMenuController() {
        return mSlideMenuController;
    }

    public void setmMenuTopController(MenuTopController mMenuTopController) {
        this.mMenuTopController = mMenuTopController;
    }

    public MenuTopController getMenuTopController() {
        return mMenuTopController;
    }

    public void lockSlideMenu() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    public void unLockSlideMenu() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }
    public void onBack(){
        if (mManager != null){
            mManager.popBackStack();
            ClientUtil.hideKeyboard();
        }
    }

    public Fragment getCurrentFragment() {
        List<Fragment> fragments = mManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    public void addFragment(BaseFragment fragment) {
        try {
            if (null != mManager) {
                String nameFragment = fragment.getClass().getName();
                FragmentTransaction ft = mManager.beginTransaction();
                ft.setCustomAnimations(
                        Rconfig.getInstance()
                                .getId("in_from_right", "anim"),
                        Rconfig.getInstance().getId("out_to_left", "anim"),
                        Rconfig.getInstance().getId("in_from_left", "anim"),
                        Rconfig.getInstance().getId("out_to_right", "anim"));
                ft.replace(Rconfig.getInstance().id("container"), fragment);
                ft.addToBackStack(nameFragment);
                ft.commit();
                mManager.executePendingTransactions();
            }
        } catch (Exception e) {
            Log.e("BaseManager","exception add fragment");
        }
    }

    public void replaceFragment(BaseFragment fragment) {
        if (mManager != null) {
            String nameFragment = fragment.getClass().getName();
            boolean isHome = false;
            if (nameFragment.equals("com.hia.home.fragment.HomeFragment")) {
                isHome = true;
            }
            if(isHome) {
                List<Fragment> list = BaseManager.getIntance().getManager()
                        .getFragments();
                if (list != null && list.size() > 0) {
                    Fragment fragment1 = list.get(list.size() - 1);
                    if(fragment1 != null && fragment1.getClass().getName().contains("Home")){
                        return;
                    }
                }
            }
            mManager.popBackStack(nameFragment,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = mManager
                    .beginTransaction();
            if (!isHome) {
                fragmentTransaction.setCustomAnimations(
                        Rconfig.getInstance().getId("in_from_right",
                                "anim"),
                        Rconfig.getInstance().getId("out_to_left",
                                "anim"),
                        Rconfig.getInstance().getId("in_from_left",
                                "anim"),
                        Rconfig.getInstance().getId("out_to_right",
                                "anim"));
            }
            fragmentTransaction.replace(
                    Rconfig.getInstance().id("container"), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    public void replaceFragmentNoAnimation(BaseFragment fragment) {
        if (mManager != null) {
            String nameFragment = fragment.getClass().getName();
            mManager.popBackStack(nameFragment,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction fragmentTransaction = mManager
                    .beginTransaction();
            fragmentTransaction.replace(
                    Rconfig.getInstance().id("container"), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }


}
