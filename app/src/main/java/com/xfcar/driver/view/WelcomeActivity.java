package com.xfcar.driver.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


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
        }, 500);
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

}
