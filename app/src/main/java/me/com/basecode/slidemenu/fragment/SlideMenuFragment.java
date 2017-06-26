package me.com.basecode.slidemenu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.com.basecode.R;
import me.com.basecode.base.fragment.BaseFragment;
import me.com.basecode.slidemenu.delegate.CloseSlideMenuDelegate;

/**
 * Created by truongtechno on 26/06/2017.
 */

public class SlideMenuFragment extends BaseFragment implements CloseSlideMenuDelegate {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected View mFragmentContainerView;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.core_slidemenu_layout, container, false);
        return rootView;
    }

    public void setup(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
                R.drawable.ic_menu, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//				getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//				getActivity().supportInvalidateOptionsMenu();
            }
        };

        // mDrawerLayout.openDrawer(mFragmentContainerView);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        initView();
    }

    public void initView(){
        PhoneSlideMenuFragment fragment = new PhoneSlideMenuFragment();
        fragment.setCloseSlideMenuDelegate(this);
        replaceFragment(fragment);
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        int container = R.id.contain_slidemenu;
        transaction.replace(container, fragment);
        transaction.commit();
    }


    public void openMenu() {
        mDrawerLayout.openDrawer(mFragmentContainerView);
    }

    public boolean checkNavigation(){
        if (mDrawerLayout.isDrawerOpen(mFragmentContainerView)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void closeSlideMenu() {
        mDrawerLayout.closeDrawer(mFragmentContainerView);
    }



}
