package com.example.user.srs;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.user.srs.R;
import com.example.user.srs.Response;
import com.example.user.srs.User;
import com.example.user.srs.NetworkUtil;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.example.user.srs.validation.validateCnfPassword;
import static com.example.user.srs.validation.validateEmail;
import static com.example.user.srs.validation.validateName;
import static com.example.user.srs.validation.validatePassword;
import static com.example.user.srs.validation.validatePhone;
import static com.example.user.srs.validation.validateCity;
import static com.example.user.srs.validation.validateAdhar;


/**
 * Created by USER on 15-03-2018.
 */

public class RegisterFragmet extends Fragment{

    public static final String TAG = RegisterFragmet.class.getSimpleName();

    private EditText mEtName;
    private EditText mEtEmail;
    private EditText mEtPhone;
    private EditText mEtPassword;
    private EditText mEtCnfPassword;
    private EditText mEtCity;
    private EditText mEtAdhar;
    private Button   mBtRegister;
    private TextView mTvLogin;
    private TextInputLayout mTiName;
    private TextInputLayout mTiEmail;
    private TextInputLayout mTiPhone;
    private TextInputLayout mTiPassword;
    private TextInputLayout mTiCnfPassword;
    private TextInputLayout mTiCity;
    private TextInputLayout mTiAdhar;
    private ProgressBar mProgressbar;

    private CompositeSubscription mSubscriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        mSubscriptions = new CompositeSubscription();
        initViews(view);
        return view;
    }

    private void initViews(View v) {

        mEtName = (EditText) v.findViewById(R.id.et_name);
        mEtEmail = (EditText) v.findViewById(R.id.et_email);
        mEtPhone = (EditText) v.findViewById(R.id.et_mobile);
        mEtPassword = (EditText) v.findViewById(R.id.et_password);
        mEtCnfPassword = (EditText) v.findViewById(R.id.et_cnfpsw);
        mEtCity = (EditText) v.findViewById(R.id.et_city);
        mEtAdhar = (EditText) v.findViewById(R.id.et_adhar);

        mBtRegister = (Button) v.findViewById(R.id.btn_register);
        mTvLogin = (TextView) v.findViewById(R.id.tv_login);

        mTiName = (TextInputLayout) v.findViewById(R.id.ti_name);
        mTiEmail = (TextInputLayout) v.findViewById(R.id.ti_email);
        mTiPhone = (TextInputLayout) v.findViewById(R.id.ti_phone);
        mTiPassword = (TextInputLayout) v.findViewById(R.id.ti_password);
        mTiCity = (TextInputLayout) v.findViewById(R.id.ti_city);
        mTiAdhar = (TextInputLayout) v.findViewById(R.id.ti_adhar);
        mProgressbar = (ProgressBar) v.findViewById(R.id.progress);

        mBtRegister.setOnClickListener(view -> register());
        mTvLogin.setOnClickListener(view -> goToLogin());
    }

    private void register() {

        setError();

        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();
        String phone = mEtPhone.getText().toString();
        String password = mEtPassword.getText().toString();
        String cnfpassword = mEtCnfPassword.getText().toString();
        String city = mEtCity.getText().toString();
        String adhar = mEtAdhar.getText().toString();




        int err = 0;

        if (!validateName(name)) {

            err++;
            mTiName.setError("Name should not be empty !");
        }

        if (!validateEmail(email)) {

            err++;
            mTiEmail.setError("Email should be valid !");
        }

        if (!validatePhone(phone)) {

            err++;
            mTiName.setError("Mobile no should not be empty !");
        }

        if (!validatePassword(password)) {

            err++;
            mTiPassword.setError("Enter a valid password !");
        }

        if (!validateCnfPassword(cnfpassword)) {

            err++;
            mTiName.setError("Password is not matching!");
        }
        if (!validateCity(city)) {

            err++;
            mTiName.setError("City should not be empty !");
        }
        if (!validateAdhar(adhar)) {

            err++;
            mTiName.setError("Adhar should not be empty !");
        }


        if (err == 0) {

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            user.setCnfPassword(cnfpassword);
            user.setCity(city);
            user.setAdhar(adhar);

            mProgressbar.setVisibility(View.VISIBLE);
            registerProcess(user);

        } else {

            showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void setError() {

        mTiName.setError(null);
        mTiEmail.setError(null);
        mTiPhone.setError(null);
        mTiPassword.setError(null);
        mTiCnfPassword.setError(null);
        mTiCity.setError(null);
        mTiAdhar.setError(null);
    }

    private void registerProcess(User user) {

        mSubscriptions.add(NetworkUtil.getRetrofit().register(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Response response) {

        mProgressbar.setVisibility(View.GONE);
        showSnackBarMessage(response.getMessage());
    }

    private void handleError(Throwable error) {

        mProgressbar.setVisibility(View.GONE);

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }

    private void showSnackBarMessage(String message) {

        if (getView() != null) {

            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }

    private void goToLogin(){

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG);
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }
}





