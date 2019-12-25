package com.xfcar.driver.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xfcar.driver.MainActivity;
import com.xfcar.driver.R;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.utils.DataManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author linky
 */
public class WelcomeActivity extends BaseActivity {

    private DataManager mDataManager;
    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mDataManager = new DataManager(this);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mDataManager.isLogin()) {
                    startActivity(MainActivity.class);
                    finish();
                } else {
                    startActivityForResult(LoginActivity.class, 1000);
                }
            }
        }, 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                startActivity(MainActivity.class);
                finish();
            } else {
                finish();
            }
        }
    }

    // 系统对权限请求的响应
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1001) {
            int total = 0;
            for (int re : grantResults) {
                total += re;
            }

            if (total != 0) {
                finish();
            }
        }
    }

}
