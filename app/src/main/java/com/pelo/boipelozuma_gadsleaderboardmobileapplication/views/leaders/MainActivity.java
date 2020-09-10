package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.submit.SubmitActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // vars
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        findViewById(R.id.submit_btn).setOnClickListener(this);

        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if (position == 1) {
                            tab.setText("Skill IQ Leaders");
                        } else {
                            tab.setText("Learning Leaders");
                        }
                    }
                }).attach();
    }

    private ViewPagerAdapter createCardAdapter() {
        return new ViewPagerAdapter(this);
    }

    void goToSubmitActivity() {
        startActivity(new Intent(MainActivity.this, SubmitActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
              case R.id.submit_btn:{
                  goToSubmitActivity();
                  break;
             }
        }
    }
}
