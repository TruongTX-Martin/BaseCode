package me.com.basecode;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import me.com.basecode.base.manager.BaseManager;
import me.com.basecode.common.utils.ClientUtil;
import me.com.basecode.common.utils.OnBackPressListener;
import me.com.basecode.fragment.HomeFragment;
import me.com.basecode.menutop.fragment.MenuTopFragment;
import me.com.basecode.slidemenu.fragment.SlideMenuFragment;

public class MainActivity extends AppCompatActivity{

    private SlideMenuFragment slideMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_activity_main);
        BaseManager.getIntance().setCurrentActivity(this);
        BaseManager.getIntance().setCurrentContext(
                getApplicationContext());
        slideMenuFragment = (SlideMenuFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        slideMenuFragment.setup(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        FragmentTransaction mFragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        MenuTopFragment fragmentMenuTop = MenuTopFragment
                .newInstance(slideMenuFragment);
        mFragmentTransaction.replace(R.id.menu_top, fragmentMenuTop);
        mFragmentTransaction.commit();

        BaseManager.getIntance().setManager(getSupportFragmentManager());

        toHome();
    }

    private void toHome() {
        HomeFragment fragment = HomeFragment.newInstance();
        BaseManager.getIntance().replaceFragment(fragment);
    }


    @Override
    public void onBackPressed() {
        if (BaseManager.getIntance().getMenuTopController().getSlideMenu().checkNavigation()) {
            BaseManager.getIntance().getMenuTopController().getSlideMenu().closeSlideMenu();
            return;
        }
        List<Fragment> list = BaseManager.getIntance().getManager()
                .getFragments();
        for (int i = list.size() - 1; i > 0; i--) {
            Fragment fragment = list.get(i);
            if (fragment != null && fragment instanceof OnBackPressListener) {
                ((OnBackPressListener) fragment).onBackPress();
                return;
            }
        }
        int count = BaseManager.getIntance().getManager()
                .getBackStackEntryCount();
        if (count == 1) {
            ClientUtil.showToast("Show dialog exit app");
        } else {
            super.onBackPressed();
        }
    }
}
