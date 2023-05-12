package com.app.shopdodientu.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.app.shopdodientu.R;

public class LoadingDialog {
    private Dialog progressDialog;
    public LoadingDialog(Context context) {
        progressDialog = new Dialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setContentView(R.layout.progress_bar_layout);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
    public void show() {
        progressDialog.show();
    }
    public void dismiss() {
        progressDialog.dismiss();
    }


}
