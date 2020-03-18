package com.xfcar.driver.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.xfcar.driver.R;

import androidx.annotation.Nullable;

/**
 * @author linky
 */
public class ImageLoadHelper {

    public static void loadImage(Context context, String url, ImageView iv) {

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().error(R.mipmap.ic_launcher))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        L.i("ImageLoadHelper onLoadFailed " + e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        L.i("ImageLoadHelper onResourceReady ");
                        return false;
                    }
                })
                .into(iv);
    }
}
