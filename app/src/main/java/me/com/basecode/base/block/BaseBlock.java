package me.com.basecode.base.block;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import me.com.basecode.R;
import me.com.basecode.base.delegate.BaseDelegate;

public class BaseBlock implements BaseDelegate {

    protected View mView;
    protected Context mContext;
    protected ViewGroup viewGroup;
    protected View mImageView;
    protected ProgressDialog pd_loading;
    protected FrameLayout menuTop;
    protected LayoutInflater inflater;

    public BaseBlock(View v, Context context) {
        mContext = context;
        initBaseBlockView(v);
    }

    private void initBaseBlockView(View view) {
        mView = view;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewGroup = ((ViewGroup) mView);
        mImageView = inflater.inflate(
                R.layout.core_base_loading, viewGroup,
                false);
        pd_loading = ProgressDialog.show(mContext, null, null, true, false);
        pd_loading.setContentView(R.layout.core_base_dialog_loading);
        pd_loading.setCanceledOnTouchOutside(false);
        pd_loading.setCancelable(false);
        pd_loading.dismiss();

    }

    public BaseBlock() {
    }

    public View getView() {
        return mView;
    }

    public void initView() {

    }

    public ProgressDialog getDialogLoading() {
        return pd_loading;
    }

    public void initView(View view) {
        initBaseBlockView(view);
    }

    @Override
    public void showLoading() {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.GONE);
        }
        viewGroup.addView(mImageView);
    }

    @Override
    public void dismissLoading() {
        viewGroup.removeView(mImageView);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View view = viewGroup.getChildAt(i);
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDialogLoading() {
        pd_loading.show();
    }

    @Override
    public void dismissDialogLoading() {
        if (pd_loading != null && pd_loading.isShowing())
            pd_loading.dismiss();
    }

    @Override
    public View getRootView() {
        return mView;
    }
}
