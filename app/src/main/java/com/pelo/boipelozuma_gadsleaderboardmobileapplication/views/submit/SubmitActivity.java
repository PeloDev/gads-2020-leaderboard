package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.submit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.components.CustomAlertDialog;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.components.CustomOneActionAlertDialog;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.MyUtilityFunctions;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SubmitActivity";

    private EditText firstNameET, lastNameET, emailET, githubLinkET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstNameET = (EditText) findViewById(R.id.submit_first_name_et);
        lastNameET = (EditText) findViewById(R.id.submit_last_name_et);
        emailET = (EditText) findViewById(R.id.submit_email_et);
        githubLinkET = (EditText) findViewById(R.id.submit_github_et);

        findViewById(R.id.submit_submit_btn).setOnClickListener(this);
        findViewById(R.id.submit_nav_back_iv).setOnClickListener(this);
    }

    boolean fieldAreValid() {
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String email = emailET.getText().toString();
        String githubLink = githubLinkET.getText().toString();

        if (firstName.length() < 2) {
            firstNameET.setError("Please enter your first name");
            return false;
        } else if (lastName.length() < 2) {
            lastNameET.setError("Please enter your last name");
            return false;
        } else if (!MyUtilityFunctions.isValidEmail(email)) {
            emailET.setError("Please enter valid email address");
            return false;
        } else if (!MyUtilityFunctions.isLinkEmail(githubLink)) {
            githubLinkET.setError("Please enter valid url");
            return false;
        }

        return true;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_nav_back_iv: {
                finish();
                break;
            }
            case R.id.submit_submit_btn: {
                if (fieldAreValid()) {
                    new CustomOneActionAlertDialog(
                            SubmitActivity.this,
                            "Are you sure?",
                            "Yes",
                            new StringCallback() {
                                @Override
                                public void onResponse(String result, Boolean success) {
                                    testSubmitProject(true);
                                }
                            }
                    ).show();
                }
                break;
            }
        }
    }

    void submitProject() {
        SubmitManager.getInstance().submitProject(
                SubmitActivity.this,
                firstNameET.getText().toString(),
                lastNameET.getText().toString(),
                emailET.getText().toString(),
                githubLinkET.getText().toString(),
                new StringCallback() {
                    @Override
                    public void onResponse(String result, Boolean success) {
                        Log.d(TAG, "submitProject.onResponse: " + result);
                        if (success) {
                            Drawable successIcon = getDrawable(R.drawable.ic_check_circle_24px);
                            successIcon.setTint(getResources().getColor(android.R.color.holo_green_dark));
                            CustomAlertDialog alertDialog = new CustomAlertDialog(
                                    SubmitActivity.this,
                                    "Submission successful",
                                    successIcon
                            );
                            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    finish();
                                }
                            });
                            alertDialog.show();
                        } else {
                            Drawable successIcon = getDrawable(R.drawable.ic_error_24px);
                            successIcon.setTint(getResources().getColor(android.R.color.holo_red_dark));
                            CustomAlertDialog alertDialog = new CustomAlertDialog(
                                    SubmitActivity.this,
                                    "Submission not successful",
                                    successIcon
                            );
                            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {

                                }
                            });
                            alertDialog.show();
                        }
                    }
                }
        );
    }

    void testSubmitProject(boolean success) {
        if (success) {
            Drawable successIcon = getDrawable(R.drawable.ic_check_circle_24px);
            successIcon.setTint(getResources().getColor(android.R.color.holo_green_dark));
            CustomAlertDialog alertDialog = new CustomAlertDialog(
                    SubmitActivity.this,
                    "Submission successful",
                    successIcon
            );
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    finish();
                }
            });
            alertDialog.show();
        } else {
            Drawable successIcon = getDrawable(R.drawable.ic_error_24px);
            successIcon.setTint(getResources().getColor(android.R.color.holo_red_dark));
            CustomAlertDialog alertDialog = new CustomAlertDialog(
                    SubmitActivity.this,
                    "Submission not successful",
                    successIcon
            );
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {

                }
            });
            alertDialog.show();
        }
    }
}
