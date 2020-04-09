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
import com.xfcar.driver.utils.Utils;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SbscrbShortActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtCarBrand;
    private int mStartYear;
    private int mStartMonth;
    private int mStartDay;

    private int mEndYear;
    private int mEndMonth;
    private int mEndDay;

    private Handler mHandler = new Handler();
    private TextView mTvDatePickStart;
    private TextView mTvDatePickEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbscrb_short);
        initView();

    }

    private void initView() {
        findViewById(R.id.rl_return_back).setOnClickListener(this);
        findViewById(R.id.tv_date_pick_start).setOnClickListener(this);
        findViewById(R.id.tv_date_pick_end).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        mTvDatePickStart = findViewById(R.id.tv_date_pick_start);
        mTvDatePickEnd = findViewById(R.id.tv_date_pick_end);
        mEtCarBrand = findViewById(R.id.et_car_brand);

        initDateTime();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_return_back) {
            finish();
        } else if (id == R.id.tv_date_pick_start) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            mStartYear = year;
                            mStartMonth = month;
                            mStartDay = dayOfMonth;
                            String date = String.format(Locale.getDefault(), "%d年%d月%d日", year, (month + 1), dayOfMonth);
                            mTvDatePickStart.setText(date);
                        }
                    },
                    mStartYear, mStartMonth, mStartDay);

            DatePicker dp = datePickerDialog.getDatePicker();
            dp.setMinDate(new Date().getTime());

            Calendar c = Calendar.getInstance();
            c.set(mEndYear, mEndMonth, mEndDay);
            dp.setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();
        } else if (id == R.id.tv_date_pick_end) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            mEndYear = year;
                            mEndMonth = month;
                            mEndDay = dayOfMonth;
                            String date = String.format(Locale.getDefault(), "%d年%d月%d日", year, (month + 1), dayOfMonth);
                            mTvDatePickEnd.setText(date);
                        }
                    },
                    mEndYear, mEndMonth, mEndDay);
            Calendar c = Calendar.getInstance();
            c.set(mStartYear, mStartMonth, mStartDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
        } else if (id == R.id.btn_submit) {

            String carNo = mEtCarBrand.getText().toString();
            carNo = carNo.equals("") ? null : carNo;
            String date = String.format(Locale.getDefault(), "%s-%02d-%02d~%s-%02d-%02d",
                    mStartYear, (mStartMonth + 1), mStartDay, mEndYear, (mEndMonth + 1), mEndDay);
            mRequester.appCarShortRentAdd(mInstance, mDataManager.getUserId(), carNo, date, new ResultCallback<String>() {
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
        mStartYear = ca.get(Calendar.YEAR);
        mStartMonth = ca.get(Calendar.MONTH);
        mStartDay = ca.get(Calendar.DAY_OF_MONTH);

        String date = String.format(Locale.getDefault(), "%d年%d月%d日", mStartYear, (mStartMonth + 1), mStartDay);
        mTvDatePickStart.setText(date);
    }

}