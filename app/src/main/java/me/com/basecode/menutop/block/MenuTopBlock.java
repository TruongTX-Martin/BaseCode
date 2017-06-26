package me.com.basecode.menutop.block;

import android.content.Context;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.com.basecode.R;
import me.com.basecode.base.block.BaseBlock;
import me.com.basecode.menutop.delegate.MenuTopDelegate;

public class MenuTopBlock extends BaseBlock implements MenuTopDelegate {
    protected Context context;
    protected ImageView imgMenu;
    private TextView txtTitle;
    private RelativeLayout layoutRight;


    public MenuTopBlock(View v, Context mcontext) {
        super(v, mcontext);
        this.context = mcontext;
    }


    @Override
    public void initView() {
        imgMenu = (ImageView) mView.findViewById(R.id.img_menu);
        txtTitle = (TextView) mView.findViewById(R.id.txtTitle);
        layoutRight = (RelativeLayout) mView.findViewById(R.id.layoutRight);
    }

    @Override
    public ImageView getImageViewMenu() {
        return imgMenu;
    }
}
