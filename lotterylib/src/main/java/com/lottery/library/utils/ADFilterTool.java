package com.lottery.library.utils;

import android.content.Context;

/**
 * Created by czg on 2017/12/29.
 */

public class ADFilterTool {
    public ADFilterTool() {
    }

    public static boolean isAdRequest(Context var0, String var1) {
        String[] var2 = new String[]{};
        int var3 = var2.length;
        int var4 = 0;

        boolean var5;
        while (true) {
            var5 = false;
            if (var4 >= var3) {
                break;
            }

            if (var1.startsWith(var2[var4])) {
                var5 = true;
                break;
            }

            ++var4;
        }

        return var5;
    }
}
