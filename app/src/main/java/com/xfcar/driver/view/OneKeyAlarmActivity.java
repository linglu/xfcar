package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.utils.DataManager;

public class OneKeyAlarmActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_key_alarm);
        initView();
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        }
    }
}