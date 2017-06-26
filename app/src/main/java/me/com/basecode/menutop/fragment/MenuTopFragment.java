package me.com.basecode.menutop.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.com.basecode.R;
import me.com.basecode.base.manager.BaseManager;
import me.com.basecode.slidemenu.fragment.SlideMenuFragment;
import me.com.basecode.base.fragment.BaseFragment;
import me.com.basecode.menutop.block.MenuTopBlock;
import me.com.basecode.menutop.controller.MenuTopController;

public class MenuTopFragment extends BaseFragment {
	public View rootView;
	protected MenuTopBlock mBlock;
	protected MenuTopController mController;
	protected SlideMenuFragment mNavigationDrawerFragment;

	public static MenuTopFragment newInstance(SlideMenuFragment mNavigationDrawerFragment){
		MenuTopFragment fragment = new MenuTopFragment();
		fragment.mNavigationDrawerFragment = mNavigationDrawerFragment;
		return fragment;
	}
	
	public MenuTopFragment() {
		super();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.core_menu_top, container,false);
		rootView.setBackgroundColor(Color.parseColor("#CC3333"));
		Context mContext = getActivity();
		mBlock = new MenuTopBlock(rootView, mContext);
		mBlock.initView();
		if (mController == null) {
			mController = new MenuTopController();
			mController.setSlideMenu(mNavigationDrawerFragment);
			mController.setMenuTopDelegate(mBlock);
			mController.onStart();
		}
		BaseManager.getIntance().setmMenuTopController(mController);
		return rootView;
	}
}
