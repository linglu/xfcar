package com.xfcar.driver.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.Utils;

public class EmergencyActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private EditText mEtName;
    private EditText mEtMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);

        mEtMobile = findViewById(R.id.et_emergency_mobile);
        mEtName = findViewById(R.id.et_emergency_name);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.btn_submit) {
            if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
                toastMsg(R.string.account_hint_name);
            } else if (!Utils.isPhoneValid(mEtMobile.getText().toString().trim())) {
                toastMsg(R.string.account_tel_pattern_wrong);
                mEtMobile.setText("");
                mEtMobile.requestFocus();
            } else {
                // 开始提交
                mRequester.appUserUpdatebyuser(mInstance, mEtName.getText().toString(), mEtMobile.getText().toString(), 0,
                        new ResultCallback<String>() {
                            @Override
                            public void onSuccess(String o) {
//                                toastMsg("注册成功");
//                                Intent intent = new Intent();
//                                intent.putExtra("mobile", mEtMobile.getText().toString());
//                                setResult(RESULT_OK, intent);
//                                finish();
                            }

                            @Override
                            public void onFail(String msg) {
                                toastMsg(msg);
                            }
                        });
            }
        }
    }
}