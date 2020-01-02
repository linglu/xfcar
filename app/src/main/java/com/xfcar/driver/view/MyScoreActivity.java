package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.ScoreTypeBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.adapter.FunctionAdapter;
import com.xfcar.driver.view.adapter.ScoreAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyScoreActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvScore;
    private RecyclerView mRvProduct;
    private ScoreAdapter mScoreTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);
        initView();

        mRequester.appUserIntegralLogList(this, mDataManager.getUserId(), new ResultCallback<List<ScoreTypeBean>>() {
            @Override
            public void onSuccess(List<ScoreTypeBean> s) {
                mScoreTypeAdapter.setData(s);

                mRequester.appIntegralGoodsFindByGoodsTag(MyScoreActivity.this,
                        mDataManager.getUserId(), new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });
            }

            @Override
            public void onFail(String msg) {
                toastMsg(msg);
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvScore = findViewById(R.id.rv_score);
        mRvProduct = findViewById(R.id.rv_score_exchange);

        mRvScore.setLayoutManager(new GridLayoutManager(this, 3));
        mScoreTypeAdapter = new ScoreAdapter(this);
        mRvScore.setAdapter(mScoreTypeAdapter);

        mRvProduct.setLayoutManager(new GridLayoutManager(this, 3));
        mScoreTypeAdapter = new ScoreAdapter(this);
        mRvScore.setAdapter(mScoreTypeAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}