package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.CarInfoBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class CarInfoActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvId;
    private TextView mTvCarno;
    private TextView mTvEngineno;
    private TextView mTvBrand;
    private TextView mTvModelno;
    private TextView mTvStatus;
    private TextView mTvType;
    private TextView mTvUsername;
    private TextView mTvUserid;
    private TextView mTvCompany;
    private TextView mTvMacid;
    private TextView mTvObjectid;
    private TextView mTvDelflag;
    private TextView mTvCreateby;
    private TextView mTvCreatedate;
    private TextView mTvUpdateby;
    private TextView mTvUpdatedate;
    private DataManager mDataManager;
    private Requester mRequester = new Requester();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        initView();

        mDataManager = new DataManager(this);
        mRequester.getCarInfoByUser(mInstance, mDataManager.getUserId(), new ResultCallback<CarInfoBean>() {
            @Override
            public void onSuccess(CarInfoBean s) {
                initData(s);
            }

            @Override
            public void onFail(String msg) {
                toastMsg(msg);
            }
        });
    }

    private void initData(CarInfoBean carInfoBean) {
        mTvId.setText(String.format("id: %s", carInfoBean.id));
        mTvCarno.setText(String.format("carNo: %s", carInfoBean.carNo));
        mTvEngineno.setText(String.format("engineNo: %s", carInfoBean.engineNo));
        mTvBrand.setText(String.format("brand: %s", carInfoBean.brand));
        mTvModelno.setText(String.format("modelNo: %s", carInfoBean.modelNo));
        mTvStatus.setText(String.format("status: %s", carInfoBean.status));
        mTvType.setText(String.format("type: %s", carInfoBean.type));
        mTvUsername.setText(String.format("username: %s", carInfoBean.username));
        mTvUserid.setText(String.format("userId: %s", carInfoBean.userId));
        mTvCompany.setText(String.format("company: %s", carInfoBean.company));
        mTvMacid.setText(String.format("macid: %s", carInfoBean.macid));
        mTvObjectid.setText(String.format("objectid: %s", carInfoBean.objectid));
        mTvDelflag.setText(String.format("delFlag: %s", carInfoBean.delFlag));
        mTvCreateby.setText(String.format("createBy: %s", carInfoBean.createBy));
        mTvCreatedate.setText(String.format("createDate: %s", carInfoBean.createDate));
        mTvUpdateby.setText(String.format("updateBy: %s", carInfoBean.updateBy));
        mTvUpdatedate.setText(String.format("updateDate: %s", carInfoBean.updateDate));
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mTvId = findViewById(R.id.tv_id);
        mTvCarno = findViewById(R.id.tv_car_no);
        mTvEngineno = findViewById(R.id.tv_engine_no);
        mTvBrand = findViewById(R.id.tv_brand);
        mTvModelno = findViewById(R.id.tv_model_no);
        mTvStatus = findViewById(R.id.tv_status);
        mTvType = findViewById(R.id.tv_type);
        mTvUsername = findViewById(R.id.tv_username);
        mTvUserid = findViewById(R.id.tv_user_id);
        mTvCompany = findViewById(R.id.tv_company);
        mTvMacid = findViewById(R.id.tv_macid);
        mTvObjectid = findViewById(R.id.tv_objectid);
        mTvDelflag = findViewById(R.id.tv_del_flag);
        mTvCreateby = findViewById(R.id.tv_create_by);
        mTvCreatedate = findViewById(R.id.tv_create_date);
        mTvUpdateby = findViewById(R.id.tv_update_by);
        mTvUpdatedate = findViewById(R.id.tv_update_date);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}
