package com.xfcar.driver.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.drm.DrmStore;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.xfcar.driver.R;

import rx.functions.Action;
import rx.functions.Action1;


/**
 * @author Linky
 */
public class ConfirmDialog extends DialogFragment implements View.OnClickListener {

    private boolean mConfirmDismiss = false;
    private Button mBtnConfirm;
    private TextView mTvTitle;
    private String mTitle;

    public static ConfirmDialog newInstance(Bundle args) {
        ConfirmDialog fragment = new ConfirmDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        assert inflater != null;
        View view = inflater.inflate(R.layout.dialog_alarm_confirm, container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtnConfirm = view.findViewById(R.id.btn_confirm);
        mBtnConfirm.setOnClickListener(this);

        mTvTitle = view.findViewById(R.id.tv_title);
        if (getArguments() != null) {
            mTitle = getArguments().getString("title");
            mTvTitle.setText(mTitle);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_confirm) {
            mConfirmDismiss = true;
            dismiss();
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        Fragment parentFragment = getParentFragment();
        if (mConfirmDismiss && parentFragment instanceof Action1) {
            ((Action1) parentFragment).call(mTitle);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnDismissListener(this);
            Window window = dialog.getWindow();

            if (window != null) {
                window.setWindowAnimations(R.style.dialog_style);
//                window.setGravity(Gravity.BOTTOM);
                window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.screenHeightPixel(mContext) * 3 / 4);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
    }

}
