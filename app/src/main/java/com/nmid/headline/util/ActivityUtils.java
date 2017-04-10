/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nmid.headline.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId,String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment,tag);
        transaction.commit();
    }

//    /**
//     *
//     * 反射实现的fragment切换，实在想不出优雅的写法了
//     */
//    public static <T> void switchFragment(@NonNull FragmentManager fragmentManager,
//                                          Context context,int frameId, String tag, Class<T> tClass) {
//        checkNotNull(fragmentManager);
//        T fragment=(T)fragmentManager.findFragmentByTag(tag);
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        if (fragment==null){
//            try {
//                fragment=(T) tClass.newInstance();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            transaction.add(frameId, (Fragment) fragment,tag);
//            transaction.commit();
//        }else {
//            transaction.show((Fragment)fragment);
//            transaction.commit();
//        }
//
//
//    }

}
