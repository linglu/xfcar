package com.xfcar.driver;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xfcar.driver.mvp.BaseActivity;
import com.xfcar.driver.utils.DataManager;
import com.xfcar.driver.view.LoginActivity;
import com.xfcar.driver.view.fragment.HomePageFragment;
import com.xfcar.driver.view.fragment.MineFragment;
import com.xfcar.driver.view.fragment.SendBillFragment;

public class MainActivity extends BaseActivity {

    private DataManager mDataManager;
    private FrameLayout mFlContainer;

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
        mFlContainer = findViewById(R.id.fl_container);
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
    };

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(ps, 1001);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) {

            }
        }
    }
}
