package com.xfcar.driver.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xfcar.driver.R;
import com.xfcar.driver.model.bean.QRCodeBean;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.network.Requester;
import com.xfcar.driver.network.ResultCallback;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.ImageLoadHelper;
import com.xfcar.driver.utils.L;

public class ReceiptActivity extends BaseActivity implements View.OnClickListener {

    private Requester mRequester = new Requester();
    private DataManager mDataManager;
    private ImageView mIvQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        initView();
        mRequester.getPaymentSource(mInstance, new ResultCallback<QRCodeBean>() {
            @Override
            public void onSuccess(QRCodeBean qrCodeBean) {
                L.i("QR code : " + qrCodeBean.qrCode);
                ImageLoadHelper.loadImage(ReceiptActivity.this, qrCodeBean.qrCode, mIvQrCode);
            }

            @Override
            public void onFail(String msg) {
                toastMsg("获取失败，请稍后再试");
            }
        });
    }

    private void initView() {
        findViewById(R.id.iv_return_back).setOnClickListener(this);
        mIvQrCode = findViewById(R.id.iv_receipt_qrcode);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_return_back) {
            finish();
        }
    }
}