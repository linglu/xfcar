package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.viewbean.ScoreProductBean;
import com.xfcar.driver.model.viewbean.ScoreRespBean;
import com.xfcar.driver.model.viewbean.ScoreTypeBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.DialogLoading;
import com.xfcar.driver.view.adapter.FunctionAdapter;
import com.xfcar.driver.view.adapter.ScoreAdapter;
import com.xfcar.driver.view.adapter.ScoreProductAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rx.functions.Action1;

public class MyScoreActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView mRvScore;
    private RecyclerView mRvProduct;
    private ScoreAdapter mScoreTypeAdapter;
    private ScoreProductAdapter mScoreProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);
        initView();

        mRequester.userIntegrallogFind(this, mDataManager.getUserId(), new ResultCallback<ScoreRespBean>() {
            @Override
            public void onSuccess(ScoreRespBean s) {
                mScoreTypeAdapter.setData(s.list);

                mRequester.appIntegralGoodsFindByGoodsTag(MyScoreActivity.this,
                        mDataManager.getUserId(), new ResultCallback<List<ScoreProductBean>>() {
                    @Override
                    public void onSuccess(List<ScoreProductBean> s) {
                        mScoreProductAdapter.setData(s);
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
            }

            @Override
            public void onFail(String msg) {
                DialogLoading.dismiss();
                toastMsg(msg);
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mRvScore = findViewById(R.id.rv_score);
        mRvProduct = findViewById(R.id.rv_score_exchange);

        mRvScore.setLayoutManager(new GridLayoutManager(this, 4));
        mScoreTypeAdapter = new ScoreAdapter(this);
        mRvScore.setAdapter(mScoreTypeAdapter);

        mRvProduct.setLayoutManager(new LinearLayoutManager(this));
        mScoreProductAdapter = new ScoreProductAdapter(this);
        mRvProduct.setAdapter(mScoreProductAdapter);
        mScoreProductAdapter.setCallback(new Action1<ScoreProductBean>() {
            @Override
            public void call(ScoreProductBean scoreProductBean) {
                mRequester.appUserIntegralGoodsOrderExchange(MyScoreActivity.this,
                        mDataManager.getUserId(), scoreProductBean.goodsName, new ResultCallback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        toastMsg("兑换成功");
                    }

                    @Override
                    public void onFail(String msg) {
                        toastMsg(msg);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}