package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.adapterbean.RepairBean;
import com.xfcar.driver.mvp.BaseActivity;

public class RepairDetailActivity extends BaseActivity implements View.OnClickListener {

    private RepairBean mRepairBean;
    private TextView mTvId;
    private TextView mTvUserid;
    private TextView mTvUsername;
    private TextView mTvCarno;
    private TextView mTvAmount;
    private TextView mTvMaintaintime;
    private TextView mTvMaintainname;
    private TextView mTvMaintainlocation;
    private TextView mTvRepairitem;
    private TextView mTvDelflag;
    private TextView mTvCreateby;
    private TextView mTvCreatedate;
    private TextView mTvUpdateby;
    private TextView mTvUpdatedate;
    private TextView mTvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_detail);
        if (getIntent().getExtras() != null) {
            mRepairBean = getIntent().getExtras().getParcelable("repair_bean");

            initView();
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        mTvId.setText(String.format("id: %s", mRepairBean.id));
        mTvUserid.setText(String.format("userId: %s", mRepairBean.userId));
        mTvUsername.setText(String.format("userName: %s", mRepairBean.userName));
        mTvCarno.setText(String.format("carNo: %s", mRepairBean.carNo));
        mTvAmount.setText(String.format("amount: %s", mRepairBean.amount));
        mTvMaintaintime.setText(String.format("maintainTime: %s", mRepairBean.maintainTime));
        mTvMaintainname.setText(String.format("maintainName: %s", mRepairBean.maintainName));
        mTvMaintainlocation.setText(String.format("maintainLocation: %s", mRepairBean.maintainLocation));
        mTvRepairitem.setText(String.format("repairItem: %s", mRepairBean.repairItem));
        mTvDelflag.setText(String.format("delFlag: %s", mRepairBean.delFlag));
        mTvCreateby.setText(String.format("createBy: %s", mRepairBean.createBy));
        mTvCreatedate.setText(String.format("createDate: %s", mRepairBean.createDate));
        mTvUpdateby.setText(String.format("updateBy: %s", mRepairBean.updateBy));
        mTvUpdatedate.setText(String.format("updateDate: %s", mRepairBean.updateDate));

    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mTvId = findViewById(R.id.tv_id);
        mTvUserid = findViewById(R.id.tv_user_id);
        mTvUsername = findViewById(R.id.tv_user_name);
        mTvCarno = findViewById(R.id.tv_car_no);
        mTvAmount = findViewById(R.id.tv_amount);
        mTvMaintaintime = findViewById(R.id.tv_maintain_time);
        mTvMaintainname = findViewById(R.id.tv_maintain_name);
        mTvMaintainlocation = findViewById(R.id.tv_maintain_location);
        mTvRepairitem = findViewById(R.id.tv_repair_item);
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
