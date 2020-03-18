package com.xfcar.driver.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.ToastUtils;

public class AdviceMsgActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_msg);
        initView();
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);

        mEtFeedback = findViewById(R.id.ed_feedback_content);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        } else if (id == R.id.btn_submit) {
            String message = mEtFeedback.getText().toString();
            if (TextUtils.isEmpty(message)) {
                return;
            }

            mRequester.alarmRecordFeedback(AdviceMsgActivity.this, message, new ResultCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    ToastUtils.show(AdviceMsgActivity.this, "提交成功");
                    finish();
                }

                @Override
                public void onFail(String msg) {
                    ToastUtils.show(AdviceMsgActivity.this, "提交失败，请稍后再试");
                }
            });
        }
    }
}