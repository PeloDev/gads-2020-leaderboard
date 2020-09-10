package com.pelo.boipelozuma_gadsleaderboardmobileapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class APIClient {

    private static final String TAG = "APIClient";

    public static void makeRequest(final Context context, final int requestMethod, final HashMap<String, String> inputMap, final String URL, final StringCallback callback) {
        String url = URL;
        if (requestMethod == Request.Method.GET){
            url += "?";
            Iterator it = inputMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                url += pair.getKey() + "=" + pair.getValue() + "&";
                it.remove(); // avoids a ConcurrentModificationException
            }

            url = url.substring(0, url.length() - 1);
            Log.d(TAG, "URL_IRL: " + url);
        }
        StringRequest stringRequest = new StringRequest(requestMethod,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "onResponse: URL = ");
                        callback.onResponse(response, true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.getMessage());
                        callback.onResponse(error.getMessage(), false);
                        /*if (context instanceof MainActivity) {
                            manageNetworkDisconnection(context);
                        }*/
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();

                params = inputMap;
                int wait = 0;

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static boolean isNetworkAvailable(final Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void manageNetworkDisconnection(Context context){
        if (!isNetworkAvailable(context)){
            // TODO: Do something if there is no network
        }
    }

}
