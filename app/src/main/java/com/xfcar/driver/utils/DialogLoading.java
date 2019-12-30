package com.xfcar.driver.utils;


import com.xfcar.driver.view.fragment.LoadingDialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author linky
 */
public class DialogLoading {

    private static LoadingDialogFragment mDialog;

    public static void show(FragmentManager fm) {
        mDialog = LoadingDialogFragment.newInstance(null);
        mDialog.show(fm, "");
    }

    public static boolean isShowing() {
        return mDialog.isVisible();
    }

    public static void dismiss() {
        if (mDialog != null && !mDialog.isRemoving()) {
            mDialog.dismiss();
        }
    }
}
