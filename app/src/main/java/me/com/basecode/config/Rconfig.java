package me.com.basecode.config;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import me.com.basecode.base.manager.BaseManager;
public class Rconfig {
    public Context mContext;
    private static final Rconfig instance = new Rconfig();

    private Rconfig() {
        mContext = BaseManager.getIntance().getCurrentContext();
    }

    public static Rconfig getInstance() {
        return instance;
    }

    public String getPackageName() {
        return mContext.getPackageName();
    }

    public int layout(String name) {
        return mContext.getResources().getIdentifier(name, "layout",
                getPackageName());
    }

    public int getId(Context context, String name, String res) {
        return context.getResources()
                .getIdentifier(name, res, getPackageName());
    }

    public int id(String name) {
        return mContext.getResources().getIdentifier(name, "id",
                getPackageName());
    }

    public int drawable(String name) {
        return mContext.getResources().getIdentifier(name, "drawable",
                getPackageName());
    }

    public int string(String name) {
        return mContext.getResources().getIdentifier(name, "string",
                getPackageName());
    }

    public int getId(String name, String res) {
        return mContext.getResources().getIdentifier(name, res,
                getPackageName());
    }


    public void setImageNotFit(ImageView image, String url) {
        Glide.with(mContext).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(image);
    }

    public String getStringLanguage(int i) {
        return BaseManager.getIntance().getCurrentActivity().getResources().getString(i);
    }

    public void setImageColor(ImageView imageView, int color) {
        if (imageView != null) {
            imageView.setColorFilter(color);
        }
    }
}
