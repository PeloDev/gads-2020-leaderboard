package com.pelo.boipelozuma_gadsleaderboardmobileapplication.views.submit;

import android.content.Context;

import com.android.volley.Request;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.APIClient;
import com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils.StringCallback;

import java.util.HashMap;

public class SubmitManager {
    private static final SubmitManager ourInstance = new SubmitManager();

    static SubmitManager getInstance() {
        return ourInstance;
    }

    private SubmitManager() {
    }

    void submitProject(Context context, String firstName, String lastName, String emailAddress, String githubLink, final StringCallback callback) {
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
        HashMap<String, String> params = new HashMap<>();
        params.put("entry.1877115667", firstName);
        params.put("entry.2006916086", lastName);
        params.put("entry.1824927963", emailAddress);
        params.put("entry.284483984", githubLink);

        APIClient.makeRequest(
                context,
                Request.Method.POST,
                params,
                url,
                new StringCallback() {
                    @Override
                    public void onResponse(String result, Boolean success) {
                        callback.onResponse(result, result.toLowerCase().contains("your response has been recorded"));
                    }
                }
        );
    }
}
