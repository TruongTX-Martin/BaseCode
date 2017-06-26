package me.com.basecode.slidemenu.block;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import me.com.basecode.R;
import me.com.basecode.base.block.BaseBlock;
import me.com.basecode.config.Rconfig;
import me.com.basecode.slidemenu.adapter.NavigationAdapter;
import me.com.basecode.slidemenu.delegate.PhoneSlideMenuDelegate;
import me.com.basecode.slidemenu.entity.ItemNavigation;
/**
 * Created by truongtechno on 26/06/2017.
 */

public class PhoneSlideMenuBlock extends BaseBlock implements PhoneSlideMenuDelegate {

    private RecyclerView recyclerView;
    private List<ItemNavigation> listItemNavigation = new ArrayList<>();
    private NavigationAdapter navigationAdapter;

    public PhoneSlideMenuBlock(View v, Context context) {
        super(v, context);
    }


    @Override
    public void initView() {
        super.initView();
        initData();
        recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        navigationAdapter = new NavigationAdapter(mContext,listItemNavigation);
        recyclerView.setAdapter(navigationAdapter);

    }

    private void initData(){
        initHome();
        initPersonal();
    }

    private void initHome(){
        int index = checkElement(Rconfig.getInstance().getStringLanguage(R.string.home));
        if (index == -1) {
            ItemNavigation item = new ItemNavigation();
            item.setName(Rconfig.getInstance().getStringLanguage(R.string.home));
            int id_icon = Rconfig.getInstance().drawable("ic_home");
            Drawable icon = mContext.getResources().getDrawable(id_icon);
            item.setDrawble(icon);
            listItemNavigation.add(item);
        }
    }

    private void initPersonal(){
        int index = checkElement(Rconfig.getInstance().getStringLanguage(R.string.personal));
        if (index == -1) {
            ItemNavigation item = new ItemNavigation();
            item.setName(Rconfig.getInstance().getStringLanguage(R.string.personal));
            int id_icon = Rconfig.getInstance().drawable("ic_home");
            Drawable icon = mContext.getResources().getDrawable(id_icon);
            item.setDrawble(icon);
            listItemNavigation.add(item);
        }
    }


    protected int checkElement(String name) {
        if (null != listItemNavigation || listItemNavigation.size() > 0) {
            for (int i = 0; i < listItemNavigation.size(); i++) {
                ItemNavigation item = listItemNavigation.get(i);
                if (item.getName().equals(name)) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }


    @Override
    public List<ItemNavigation> getListItemNavigation() {
        return listItemNavigation;
    }

    @Override
    public RecyclerView getRecycleView() {
        return recyclerView;
    }
}
