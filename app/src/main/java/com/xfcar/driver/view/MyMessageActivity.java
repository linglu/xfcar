package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;

import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.Message;
import com.xfcar.driver.model.viewbean.MsgBean;
import com.xfcar.driver.model.viewbean.RewardItemBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.view.adapter.MsgAdapter;
import com.xfcar.driver.view.adapter.RewardAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyMessageActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvMsg;
    private MsgAdapter mMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        initView();

        mRequester.userMessageGet(this, true, mDataManager.getUserId(), new ResultCallback<List<Message>>() {
            @Override
            public void onSuccess(List<Message> messages) {
                mMsgAdapter.setData(messages);
            }

            @Override
            public void onFail(String msg) {
                L.i("MyMessageActivity onFail " + msg);
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        findViewById(R.id.ll_leave_msg).setOnClickListener(this);
        findViewById(R.id.ll_contact_service).setOnClickListener(this);
        mRvMsg = findViewById(R.id.rv_message);

        mMsgAdapter = new MsgAdapter(this);
        mRvMsg.setLayoutManager(new LinearLayoutManager(this));
        mRvMsg.setAdapter(mMsgAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        } else if (id == R.id.ll_leave_msg) {
            startActivity(AdviceMsgActivity.class);
        } else if (id == R.id.ll_contact_service) {
            startActivity(ContactUsActivity.class);
        }
    }
}