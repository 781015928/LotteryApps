package com.lottery.app;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by czg on 2017/12/29.
 */

public class Utils {
    public static String getAppInfo(Context paramContext) {
        try {
            String str1 = paramContext.getPackageName();
            String str2 = paramContext.getPackageManager().getPackageInfo(str1, 0).versionName;
            int i = paramContext.getPackageManager().getPackageInfo(str1, 0).versionCode;
            String str3 = str1 + "   " + str2 + "  " + i;
            return str3;
        } catch (Exception localException) {
        }
        return null;
    }

    public static void navigateTo(@NonNull WebContentIntentBuilder paramWebContentIntentBuilder) {
        paramWebContentIntentBuilder.getContext().startActivity(paramWebContentIntentBuilder.build());
    }
}
