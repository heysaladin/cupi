package com.codingdemos.vacapedia.widgets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

import com.codingdemos.flowers.R;

public class BusyDialog {

    private final Dialog dialog;
    private TextView busyText;

    @SuppressLint("SetTextI18n")
    public BusyDialog(Context c, boolean cancelable, String text) {
        dialog = new Dialog(c, R.style.AppTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // here we set layout of progress dialog
        dialog.setContentView(R.layout.layout_progress);
        dialog.setCancelable(cancelable);
        busyText = (TextView) dialog.findViewById(R.id.authenticating_status_tv);
        busyText.setText(text + "");
        busyText.setTextColor(c.getResources().getColor(R.color.colorPrimary));

    }

    public BusyDialog(Activity c, String message) {
        dialog = new Dialog(c,
                android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // here we set layout of progress dialog
        dialog.setContentView(R.layout.layout_progress);
        dialog.setCancelable(false);
        busyText = (TextView) dialog.findViewById(R.id.authenticating_status_tv);
        busyText.setText(message);
        busyText.setTextColor(c.getResources().getColor(R.color.colorPrimary));

    }

    @SuppressLint("SetTextI18n")
    public BusyDialog(Activity c, boolean cancelable, String text, int textColor) {
        dialog = new Dialog(c,
                android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // here we set layout of progress dialog
        dialog.setContentView(R.layout.layout_progress);
        dialog.setCancelable(cancelable);
        busyText = (TextView) dialog.findViewById(R.id.authenticating_status_tv);
        busyText.setText(text + "");
        busyText.setTextColor(textColor);

    }

    @SuppressLint("SetTextI18n")
    public BusyDialog(Activity c, boolean cancelable, String text,
                      int textColor, int time) {
        dialog = new Dialog(c,
                android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // here we set layout of progress dialog
        dialog.setContentView(R.layout.layout_progress);
        dialog.setCancelable(cancelable);
        busyText = (TextView) dialog.findViewById(R.id.authenticating_status_tv);
        busyText.setText(text + "");
        busyText.setTextColor(textColor);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing()) {
                    dialog.dismiss();

                }
            }
        };

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, time);

    }

    public BusyDialog(Activity c, boolean cancelable) {
        dialog = new Dialog(c,
                android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // here we set layout of progress dialog
        dialog.setContentView(R.layout.layout_progress);
        dialog.setCancelable(cancelable);
    }

    public void show() {
        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
