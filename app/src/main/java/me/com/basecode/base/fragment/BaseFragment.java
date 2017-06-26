package me.com.basecode.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {
	
	protected View rootView;
	protected String screenName = "";
	protected Context context;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String name) {
		this.screenName = name;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
