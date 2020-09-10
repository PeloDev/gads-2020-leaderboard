package com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class MyUtilityFunctions {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isLinkEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.WEB_URL.matcher(target).matches());
    }
}
