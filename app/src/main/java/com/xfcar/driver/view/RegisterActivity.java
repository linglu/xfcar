package com.xfcar.driver.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.utils.Utils;

/**
 * @author Linky
 */
public class RegisterActivity extends BaseActivity {

    private EditText mEtName;
    private EditText mEtPwd;
    private EditText mEtMobile;
    private ImageView mIvEyes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEtName = findViewById(R.id.et_name_register);
        mEtMobile = findViewById(R.id.et_phone_number);
        mEtPwd = findViewById(R.id.et_setPwd_register);

        mIvEyes = findViewById(R.id.iv_eyes_register);

        findViewById(R.id.rl_eyeLayout_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIvEyes.isSelected()) {
                    mIvEyes.setSelected(false);
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mEtPwd.setSelection(mEtPwd.getText().length());
                } else {
                    mIvEyes.setSelected(true);
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mEtPwd.setSelection(mEtPwd.getText().length());
                }
            }
        });

        findViewById(R.id.bt_register_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
                    toastMsg(R.string.account_hint_name);
                } else if (!Utils.isPhoneValid(mEtMobile.getText().toString().trim())) {
                    toastMsg(R.string.account_tel_pattern_wrong);
                    mEtMobile.setText("");
                    mEtMobile.requestFocus();
                } else if (TextUtils.isEmpty(mEtPwd.getText().toString().trim())) {
                    toastMsg(R.string.account_hint_input_pwd);
                    mEtPwd.setText("");
                    mEtPwd.requestFocus();
                } else if (mEtPwd.getText().length() < 6 || mEtPwd.getText().length() > 12) {
                    toastMsg(R.string.me_hint_pwd_format_error);
                    mEtPwd.requestFocus();
                } else {

                    // 开始注册
                    mRequester.register(mInstance, mEtName.getText().toString(), mEtMobile.getText().toString(),
                            mEtPwd.getText().toString(), new ResultCallback<Object>() {
                                @Override
                                public void onSuccess(Object o) {
                                    toastMsg("注册成功");
                                    Intent intent = new Intent();
                                    intent.putExtra("mobile", mEtMobile.getText().toString());
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }

                                @Override
                                public void onFail(String msg) {
                                    toastMsg(msg);
                                }
                            });
                }

            }
        });
    }
}
