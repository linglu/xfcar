package com.xfcar.driver.view.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.victor.loading.rotate.RotateLoading;
import com.xfcar.driver.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author linky
 */
public class LoadingDialogFragment extends DialogFragment {

    private RotateLoading mBookLoading;

    public static LoadingDialogFragment newInstance(Bundle bundle) {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.dialog_loading, container);
        mBookLoading = view.findViewById(R.id.rl_loading);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
//            dialog.setOnKeyListener(this);
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBookLoading.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBookLoading.stop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
