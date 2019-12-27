package com.xfcar.driver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.utils.L;
import com.xfcar.driver.utils.Utils;
import com.xfcar.driver.view.LoginActivity;
import com.xfcar.driver.view.fragment.HomePageFragment;
import com.xfcar.driver.view.fragment.MineFragment;
import com.xfcar.driver.view.fragment.SendBillFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {

    private DataManager mDataManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new HomePageFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new SendBillFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new MineFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, new HomePageFragment()).commit();

        mDataManager = new DataManager(this);
        if (!mDataManager.isLogin()) {
            startActivityForResult(LoginActivity.class, 1001);
        }
    }

    private String[] ps = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
    };

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(ps, 1001);
            } else {
                L.i("IMEI : " + Utils.getIMEI(this));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(resultCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {

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
            } else {
                L.i("IMEI : " + Utils.getIMEI(this));
            }
        }
    }
}
