package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders.HoursLeaderFragment;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders.SkillIQLeaderFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        if (position == 1) {
            return new SkillIQLeaderFragment();
        } else {
            return new HoursLeaderFragment();
        }
    }
    @Override public int getItemCount() {
        return 2;
    }
}
