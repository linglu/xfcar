package com.xfcar.driver.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xfcar.driver.R;

/**
 * @author linky
 */
public class ImageLoadHelper {

    public static void loadImage(Context context, String url, ImageView iv) {

        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher))
                .apply(new RequestOptions().error(R.mipmap.ic_return_back))
                .into(iv);
    }
}
