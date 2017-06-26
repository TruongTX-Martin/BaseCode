package me.com.basecode.slidemenu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.com.basecode.R;
import me.com.basecode.base.fragment.BaseFragment;
import me.com.basecode.base.manager.BaseManager;
import me.com.basecode.slidemenu.block.PhoneSlideMenuBlock;
import me.com.basecode.slidemenu.controller.PhoneSlideMenuController;
import me.com.basecode.slidemenu.delegate.CloseSlideMenuDelegate;

/**
 * Created by truongtechno on 26/06/2017.
 */

public class PhoneSlideMenuFragment extends BaseFragment {


    private PhoneSlideMenuBlock block;
    private PhoneSlideMenuController controller;

    private CloseSlideMenuDelegate closeSlideMenuDelegate;

    public void setCloseSlideMenuDelegate(CloseSlideMenuDelegate closeSlideMenuDelegate) {
        this.closeSlideMenuDelegate = closeSlideMenuDelegate;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.core_phone_slide_menu, container, false);
        Context context = getActivity();

        block = new PhoneSlideMenuBlock(rootView,context);
        block.initView();

        controller = new PhoneSlideMenuController();
        controller.setDelegate(block);
        controller.setCloseSlideMenuDelegate(closeSlideMenuDelegate);
        controller.onStart();

        BaseManager.getIntance().setSlideMenuController(controller);
        return rootView;
    }
}
