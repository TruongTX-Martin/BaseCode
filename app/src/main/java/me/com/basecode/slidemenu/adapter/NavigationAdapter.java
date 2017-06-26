package me.com.basecode.slidemenu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.com.basecode.R;
import me.com.basecode.common.utils.ClientUtil;
import me.com.basecode.slidemenu.entity.ItemNavigation;

/**
 * Created by root on 25/04/2017.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationView> {

    private Context context;
    private List<ItemNavigation> listNavigation;
    private LayoutInflater inflater;

    public NavigationAdapter(Context context, List<ItemNavigation> list) {
        this.context = context;
        this.listNavigation = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NavigationView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.core_phone_slide_menu_item, parent, false);
        return new NavigationAdapter.NavigationView(view);
    }

    @Override
    public void onBindViewHolder(NavigationView holder, int position) {
        ItemNavigation entity = listNavigation.get(position);
        if (entity != null) {
            if (ClientUtil.validate(entity.getName())) {
                holder.txtName.setText(entity.getName());
            }

            if (entity.getDrawble() != null) {
                holder.imgIcon.setImageDrawable(entity.getDrawble());
            }
        }
    }

    @Override
    public int getItemCount() {
        return listNavigation.size();
    }

    public class NavigationView extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView txtName;

        public NavigationView(View view) {
            super(view);
            imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
            txtName = (TextView) view.findViewById(R.id.txtName);
        }
    }
}
