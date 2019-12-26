package com.xfcar.driver.view;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.ClaimPayBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class ClaimPayDetailActivity extends BaseActivity implements View.OnClickListener {

    private ClaimPayBean mClaimPayBean;
    private TextView mTvId;
    private TextView mTvUserid;
    private TextView mTvUsername;
    private TextView mTvIdcardnumber;
    private TextView mTvCarno;
    private TextView mTvAmount;
    private TextView mTvAccidenttime;
    private TextView mTvAccidentlocation;
    private TextView mTvRepairlocation;
    private TextView mTvRepairdays;
    private TextView mTvDelflag;
    private TextView mTvCreateby;
    private TextView mTvCreatedate;
    private TextView mTvUpdateby;
    private TextView mTvUpdatedate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_pay_detail);
        if (getIntent().getExtras() != null) {
            mClaimPayBean = getIntent().getExtras().getParcelable("claim_pay_bean");

            initView();
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        mTvId.setText(String.format("id: %s", mClaimPayBean.id));
        mTvUserid.setText(String.format("userId: %s", mClaimPayBean.userId));
        mTvUsername.setText(String.format("userName: %s", mClaimPayBean.userName));
        mTvIdcardnumber.setText(String.format("idCardNumber: %s", mClaimPayBean.idCardNumber));
        mTvCarno.setText(String.format("carNo: %s", mClaimPayBean.carNo));
        mTvAmount.setText(String.format("amount: %s", mClaimPayBean.amount));
        mTvAccidenttime.setText(String.format("accidentTime: %s", mClaimPayBean.accidentTime));
        mTvAccidentlocation.setText(String.format("accidentLocation: %s", mClaimPayBean.accidentLocation));
        mTvRepairlocation.setText(String.format("repairLocation: %s", mClaimPayBean.repairLocation));
        mTvRepairdays.setText(String.format("repairDays: %s", mClaimPayBean.repairDays));
        mTvDelflag.setText(String.format("delFlag: %s", mClaimPayBean.delFlag));
        mTvCreateby.setText(String.format("createBy: %s", mClaimPayBean.createBy));
        mTvCreatedate.setText(String.format("createDate: %s", mClaimPayBean.createDate));
        mTvUpdateby.setText(String.format("updateBy: %s", mClaimPayBean.updateBy));
        mTvUpdatedate.setText(String.format("updateDate: %s", mClaimPayBean.updateDate));
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);

        mTvId = findViewById(R.id.tv_id);
        mTvUserid = findViewById(R.id.tv_user_id);
        mTvUsername = findViewById(R.id.tv_user_name);
        mTvIdcardnumber = findViewById(R.id.tv_id_card_number);
        mTvCarno = findViewById(R.id.tv_car_no);
        mTvAmount = findViewById(R.id.tv_amount);
        mTvAccidenttime = findViewById(R.id.tv_accident_time);
        mTvAccidentlocation = findViewById(R.id.tv_accident_location);
        mTvRepairlocation = findViewById(R.id.tv_repair_location);
        mTvRepairdays = findViewById(R.id.tv_repair_days);
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
