package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.leaders;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pelo.boipelozuma_gadsleaderboardmobileapplication.R;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner.Learner;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner.LearnerLeaderRecyclerAdapter;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner.LearnersManager;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HoursLeaderFragment extends Fragment implements
        View.OnClickListener {

    // debug
    private static final String TAG = "LearnerLeaderFragment";

    // widgets
    private RecyclerView recyclerView;
    private LearnerLeaderRecyclerAdapter adapter;

    // vars
    private List<Learner> learners;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learner_leader_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.learner_leader_rv);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        learners = LearnersManager.getInstance().getContentLearners();
        Log.d(TAG, "setupRecyclerView: hours learners size: " + learners.size());
        if (learners.size() < 1) {
            learners = new ArrayList<>();
            LearnersManager.getInstance().setContentLearners(getActivity(), new StringCallback() {
                @Override
                public void onResponse(String result, Boolean success) {
                    if (success) {
                        learners = LearnersManager.getInstance().getContentLearners();
                        setupRecyclerView();
                    }
                }
            });
        }
        adapter = new LearnerLeaderRecyclerAdapter(
                getContext(),
                learners,
                false
        );

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                ((MainActivity)getActivity()), LinearLayoutManager.VERTICAL, false
        );

        //layoutManager.scrollToPositionWithOffset(0, 0);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(learners.size());
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //  case R.id.___:{
            //      break;
            // }
        }
    }
}
