package com.nmid.headline.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by xwysu on 2017/5/23.
 */

public class PicassoUtil {
    public static void loadNewsListImage(ImageView imageView){
        Picasso.with(imageView.getContext())
                .load(imageView.getTag().toString())
//                .memoryPolicy(MemoryPolicy.NO_CACHE)
//                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(240,135).centerCrop().into(imageView);
    }
}
