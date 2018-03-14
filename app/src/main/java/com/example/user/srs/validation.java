package com.example.user.srs;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by USER on 13-03-2018.
 */

public class validation {

    public static boolean validateFields(String name){

        if (TextUtils.isEmpty(name)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateEmail(String string) {

        if (TextUtils.isEmpty(string) || !Patterns.EMAIL_ADDRESS.matcher(string).matches()) {

            return false;

        } else {

            return true;
        }
    }



}
