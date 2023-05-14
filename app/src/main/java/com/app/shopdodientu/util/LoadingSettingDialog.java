package com.app.shopdodientu.util;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingSettingDialog {
    private ProgressDialog progressDialog;
    public LoadingSettingDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Đang cài đặt ứng dụng vui lòng chờ...");
    }
    public void show() {
        progressDialog.show();
    }
    public void dismiss() {
        progressDialog.dismiss();
    }
}
