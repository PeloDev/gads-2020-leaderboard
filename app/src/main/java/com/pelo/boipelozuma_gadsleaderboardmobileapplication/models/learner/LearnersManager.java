package com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.learner;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.models.LeaderType;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.APIClient;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearnersManager {
    private static final String TAG = "LearnersManager";
    private static final LearnersManager ourInstance = new LearnersManager();

    public static LearnersManager getInstance() {
        return ourInstance;
    }

    private LearnersManager() {
    }

    private List<Learner> contentLearners = new ArrayList<>();
    private List<Learner> skillLearners = new ArrayList<>();

    public List<Learner> getContentLearners() {
        return contentLearners;
    }

    public List<Learner> getSkillLearners() {
        return skillLearners;
    }

    public void setContentLearners(Context context, final StringCallback callback) {
        Log.d(TAG, "getContentLearners: START ===============");
        final String url = "https://gadsapi.herokuapp.com/api/hours";
        final HashMap<String, String> params = new HashMap<>();
        APIClient.makeRequest(context, Request.Method.GET, params, url, new StringCallback() {
            @Override
            public void onResponse(String result, Boolean success) {
                if (result == null){
                    Log.d(TAG, "onResponse: NULL result");
                    return;
                }
                Log.d(TAG, "onResponse: " + result);
                try {
                    JSONArray array = new JSONArray(result);
                    contentLearners = new ArrayList<>();
                    Log.d(TAG, "onResponse: JSON ARRAY SIZE = " + array.length());
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        Learner l = new Learner(
                                obj.getString("name"),
                                obj.getInt("hours"),
                                obj.getString("country"),
                                obj.getString("badgeUrl"),
                                LeaderType.HOURS
                        );
                        contentLearners.add(l);
                    }

                    callback.onResponse("success", true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onResponse: " + e.getMessage());
                    callback.onResponse("fail", false);
                }
            }
        });
    }

    public void setSkillIQLearners(Context context, final StringCallback callback) {
        Log.d(TAG, "getSkillIQLearners: START ===============");
        final String url = "https://gadsapi.herokuapp.com/api/skilliq";
        final HashMap<String, String> params = new HashMap<>();
        APIClient.makeRequest(context, Request.Method.GET, params, url, new StringCallback() {
            @Override
            public void onResponse(String result, Boolean success) {
                if (result == null){
                    Log.d(TAG, "onResponse: NULL result");
                    return;
                }
                Log.d(TAG, "onResponse: " + result);
                try {
                    JSONArray array = new JSONArray(result);
                    contentLearners = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        Learner l = new Learner(
                                obj.getString("name"),
                                obj.getInt("score"),
                                obj.getString("country"),
                                obj.getString("badgeUrl"),
                                LeaderType.SKILL_IQ
                        );
                        skillLearners.add(l);
                    }

                    callback.onResponse("success", true);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onResponse: " + e.getMessage());
                    callback.onResponse("fail", false);
                }
            }
        });
    }
}
