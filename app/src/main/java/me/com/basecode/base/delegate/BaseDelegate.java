package me.com.basecode.base.delegate;


import android.view.View;

public interface BaseDelegate {
	 void showLoading();

	 void dismissLoading();

	 void showDialogLoading();

	 void dismissDialogLoading();
	View getRootView();

}
