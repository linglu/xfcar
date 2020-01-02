package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.mybean.OursBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;

public class ContactUsActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvCompanyId;
    private TextView mTvEnLogogram;
    private TextView mTvCompanyName;
    private TextView mTvCompanyFullname;
    private TextView mTvCompanyIntroduction;
    private TextView mTvCompanyWebsite;
    private TextView mTvCompanyProduct;
    private TextView mTvCompanyImage;
    private TextView mTvCompanyAddress;
    private TextView mTvCompanyCorporate;
    private TextView mTvCompanyCode;
    private TextView mTvCompanyScope;
    private TextView mTvCompanyEmail;
    private TextView mTvCompanyTelphone;
    private TextView mTvDelFlag;
    private TextView mTvCreateDate;
    private TextView mTvCreateBy;
    private TextView mTvUpdateDate;
    private TextView mTvUpdateBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initView();
        mRequester.appCompanyInfoFind(this, new ResultCallback<OursBean>() {
            @Override
            public void onSuccess(OursBean s) {
                setData(s);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private void setData(OursBean oursBean) {
        mTvCompanyId.setText(String.format("companyId: %s", oursBean.companyId));
        mTvEnLogogram.setText(String.format("enLogogram: %s", oursBean.enLogogram));
        mTvCompanyName.setText(String.format("companyName: %s", oursBean.companyName));
        mTvCompanyFullname.setText(String.format("companyFullname: %s", oursBean.companyFullname));
        mTvCompanyIntroduction.setText(String.format("companyIntroduction: %s", oursBean.companyIntroduction));
        mTvCompanyWebsite.setText(String.format("companyWebsite: %s", oursBean.companyWebsite));
        mTvCompanyProduct.setText(String.format("companyProduct: %s", oursBean.companyProduct));
        mTvCompanyImage.setText(String.format("companyImage: %s", oursBean.companyImage));
        mTvCompanyAddress.setText(String.format("companyAddress: %s", oursBean.companyAddress));
        mTvCompanyCorporate.setText(String.format("companyCorporate: %s", oursBean.companyCorporate));
        mTvCompanyCode.setText(String.format("companyCode: %s", oursBean.companyCode));
        mTvCompanyScope.setText(String.format("companyScope: %s", oursBean.companyScope));
        mTvCompanyEmail.setText(String.format("companyEmail: %s", oursBean.companyEmail));
        mTvCompanyTelphone.setText(String.format("companyTelphone: %s", oursBean.companyTelphone));
        mTvDelFlag.setText(String.format("delFlag: %s", oursBean.delFlag));
        mTvCreateDate.setText(String.format("createDate: %s", oursBean.createDate));
        mTvCreateBy.setText(String.format("createBy: %s", oursBean.createBy));
        mTvUpdateDate.setText(String.format("updateDate: %s", oursBean.updateDate));
        mTvUpdateBy.setText(String.format("updateBy: %s", oursBean.updateBy));
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mTvCompanyId = findViewById(R.id.tv_company_id);
        mTvEnLogogram = findViewById(R.id.tv_en_logogram);
        mTvCompanyName = findViewById(R.id.tv_company_name);
        mTvCompanyFullname = findViewById(R.id.tv_company_fullname);
        mTvCompanyIntroduction = findViewById(R.id.tv_company_introduction);
        mTvCompanyWebsite = findViewById(R.id.tv_company_website);
        mTvCompanyProduct = findViewById(R.id.tv_company_product);
        mTvCompanyImage = findViewById(R.id.tv_company_image);
        mTvCompanyAddress = findViewById(R.id.tv_company_address);
        mTvCompanyCorporate = findViewById(R.id.tv_company_corporate);
        mTvCompanyCode = findViewById(R.id.tv_company_code);
        mTvCompanyScope = findViewById(R.id.tv_company_scope);
        mTvCompanyEmail = findViewById(R.id.tv_company_email);
        mTvCompanyTelphone = findViewById(R.id.tv_company_telphone);
        mTvDelFlag = findViewById(R.id.tv_del_flag);
        mTvCreateDate = findViewById(R.id.tv_create_date);
        mTvCreateBy = findViewById(R.id.tv_create_by);
        mTvUpdateDate = findViewById(R.id.tv_update_date);
        mTvUpdateBy = findViewById(R.id.tv_update_by);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}