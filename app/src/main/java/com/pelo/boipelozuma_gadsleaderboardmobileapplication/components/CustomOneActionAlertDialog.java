package com.pelo.boipelozuma_gadsleaderboardmobileapplication.components;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;


public class CustomOneActionAlertDialog extends Dialog implements View.OnClickListener {

    private Activity activity;
    private TextView textView;
    private String title, actionTitle;
    StringCallback callback;

    public CustomOneActionAlertDialog(Activity activity, String title, String actionTitle,StringCallback callback) {
        super(activity);
        this.activity = activity;
        this.title = title;
        this.actionTitle = actionTitle;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_one_action_alert_dialog);

        textView = (TextView) findViewById(R.id.custom_oa_dialog_title_tv);
        textView.setText(title);

        ((Button)findViewById(R.id.custom_oa_dialog_btn)).setText(actionTitle);
        findViewById(R.id.custom_oa_dialog_btn).setOnClickListener(this);
        findViewById(R.id.custom_oa_dialog_close_iv).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_oa_dialog_close_iv: {
                dismiss();
                break;
            }
            case R.id.custom_oa_dialog_btn: {
                callback.onResponse("Pressed " + actionTitle, true);
                dismiss();
                break;
            }
        }
    }
}
