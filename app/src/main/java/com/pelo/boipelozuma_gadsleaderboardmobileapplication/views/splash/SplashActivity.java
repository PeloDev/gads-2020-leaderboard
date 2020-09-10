package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner.LearnersManager;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setData();
    }

    void setData() {
        LearnersManager.getInstance().setContentLearners(getApplicationContext(), new StringCallback() {
            @Override
            public void onResponse(String result, Boolean success) {
                LearnersManager.getInstance().setSkillIQLearners(getApplicationContext(), new StringCallback() {
                    @Override
                    public void onResponse(String result, Boolean success) {
                        GoToMainActivity();
                    }
                });
            }
        });
    }

    void GoToMainActivity() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
