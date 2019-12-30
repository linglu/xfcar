package com.xfcar.driver.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SbscrbShortActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private ImageView mIvReturnBack;
    private TextView mTvTitle;
    private TextView mTvDatePick;
    private EditText mEtCarBrand;
    private int mYear;
    private int mMonth;
    private int mDay;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbscrb_short);
        initView();
        mDataManager = new DataManager(this);
    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        findViewById(R.id.tv_date_pick).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        mTvDatePick = findViewById(R.id.tv_date_pick);
        mEtCarBrand = findViewById(R.id.et_car_brand);

        initDateTime();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        } else if (id == R.id.tv_date_pick) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            mYear = year;
                            mMonth = month;
                            mDay = dayOfMonth;
                            String date = String.format(Locale.getDefault(), "%d年%d月%d日", mYear, (month + 1), dayOfMonth);
                            mTvDatePick.setText(date);
                        }
                    },
                    mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            datePickerDialog.show();
        } else if (id == R.id.btn_submit) {

            L.i("submit");
            String carNo = mEtCarBrand.getText().toString();
            carNo = carNo.equals("") ? null : carNo;
            String date = String.format("%s-%s-%s", mYear, (mMonth + 1), mDay);
            mRequester.appCarShortrentAdd(mInstance, mDataManager.getUserId(), carNo, date, new ResultCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    toastMsg("提交成功，稍后会有工作人员联系");
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }

                @Override
                public void onFail(String msg) {
                    toastMsg("提交失败");
                }
            });
        }
    }

    public void initDateTime() {
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        String date = String.format(Locale.getDefault(), "%d年%d月%d日", mYear, (mMonth + 1), mDay);
        mTvDatePick.setText(date);
    }

}