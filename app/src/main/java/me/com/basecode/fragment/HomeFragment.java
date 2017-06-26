package me.com.basecode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.com.basecode.R;
import me.com.basecode.base.fragment.BaseFragment;

/**
 * Created by truongtechno on 26/06/2017.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_fragment_home, container,false);
        return rootView;
    }
}
