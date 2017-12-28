package com.lottery.library.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by 78101 on 2017/9/13.
 */

public class ImageLoader {
    public static void setImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);

    }

}
