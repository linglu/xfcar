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

import java.util.List;

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
    private CarInfoBean mCarInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        if (getIntent().getExtras() != null) {
            mCarInfoBean = getIntent().getExtras().getParcelable("car_info_bean");

            initView();
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        mTvId.setText(String.format("id: %s", mCarInfoBean.id));
        mTvCarno.setText(String.format("carNo: %s", mCarInfoBean.carNo));
        mTvEngineno.setText(String.format("engineNo: %s", mCarInfoBean.engineNo));
        mTvBrand.setText(String.format("brand: %s", mCarInfoBean.brand));
        mTvModelno.setText(String.format("modelNo: %s", mCarInfoBean.modelNo));
        mTvStatus.setText(String.format("status: %s", mCarInfoBean.status));
        mTvType.setText(String.format("type: %s", mCarInfoBean.type));
        mTvUsername.setText(String.format("username: %s", mCarInfoBean.username));
        mTvUserid.setText(String.format("userId: %s", mCarInfoBean.userId));
        mTvCompany.setText(String.format("company: %s", mCarInfoBean.company));
        mTvMacid.setText(String.format("macid: %s", mCarInfoBean.macid));
        mTvObjectid.setText(String.format("objectid: %s", mCarInfoBean.objectid));
        mTvDelflag.setText(String.format("delFlag: %s", mCarInfoBean.delFlag));
        mTvCreateby.setText(String.format("createBy: %s", mCarInfoBean.createBy));
        mTvCreatedate.setText(String.format("createDate: %s", mCarInfoBean.createDate));
        mTvUpdateby.setText(String.format("updateBy: %s", mCarInfoBean.updateBy));
        mTvUpdatedate.setText(String.format("updateDate: %s", mCarInfoBean.updateDate));
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
