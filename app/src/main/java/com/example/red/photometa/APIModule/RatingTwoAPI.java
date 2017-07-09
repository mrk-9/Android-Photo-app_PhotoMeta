package com.example.red.photometa.APIModule;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by RED on 10/5/2016.
 */

public class RatingTwoAPI {

    private String url;
    private RequestParams params;

    public interface ApiSyncHandler {
        public void success(JSONObject response);
        public void failed(JSONObject response, Throwable throwable);
    }

    public ApiSyncHandler handler = null;

    public RatingTwoAPI() {}

    public RatingTwoAPI(String mUrl, RequestParams mParams, ApiSyncHandler mHandler) {

        this.url = mUrl;
        this.params = mParams;
        this.handler = mHandler;
    }

    public void syncObject()    {

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(url, this.params, new JsonHttpResponseHandler() {
               @Override
               public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                   handler.success(response);
               }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    handler.failed(errorResponse, throwable);
                }

        });
    }
}

