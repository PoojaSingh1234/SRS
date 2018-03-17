package com.example.user.srs;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 13-03-2018.
 */



public class validation {


     public static EditText mEtCfPassword;
     public  static  EditText mEtPassword;

    public static void initViews(View v) {

        mEtPassword = (EditText) v.findViewById(R.id.et_password);
        mEtCfPassword = (EditText) v.findViewById(R.id.et_cnfpsw);

        String password = mEtPassword.getText().toString();
        String cfpassword = mEtCfPassword.getText().toString();
    }

    public static boolean validateName(String name){

        if (TextUtils.isEmpty(name)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateEmail(String email) {

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validatePhone(String phone) {

        if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches()) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validatePassword(String password){

        if (TextUtils.isEmpty(password) || (password.length()<3)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateCnfPassword(String cnfpassword){



        if (TextUtils.isEmpty(cnfpassword) || (mEtCfPassword.getText().toString().equals(mEtPassword.getText().toString()))) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateCity(String city){

        if (TextUtils.isEmpty(city)) {

            return false;

        } else {

            return true;
        }
    }

    public static boolean validateAdhar(String adhar) {

        if (TextUtils.isEmpty(adhar) || (adhar.length()<12) || (adhar.length()>12)) {

            return false;

        } else {

            return true;
        }
    }



}
