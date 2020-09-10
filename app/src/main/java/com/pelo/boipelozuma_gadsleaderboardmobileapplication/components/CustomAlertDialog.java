package com.pelo.boipelozuma_gadsleaderboardmobileapplication.components;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;


public class CustomAlertDialog extends Dialog implements DialogInterface.OnClickListener {

    public Activity activity;
    public ImageView imageView;
    public TextView textView;
    String message;
    Drawable drawable;

    public CustomAlertDialog(Activity activity, String message, Drawable drawable) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.drawable = drawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_alert_dialog);

        textView = (TextView) findViewById(R.id.custom_dialog_message_tv);
        imageView = (ImageView) findViewById(R.id.custom_dialog_iv);

        textView.setText(message);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        dismiss();
    }
}
