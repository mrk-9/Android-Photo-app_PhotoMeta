package com.example.red.photometa.APIModule;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by RED on 9/29/2016.
 */

public class PhotoMetaAPI {

    private String url;
    private RequestParams params;

    public interface ApiSyncHandler {
        public void success(String response);
        public void failed(String response, Throwable throwable);
    }

    public ApiSyncHandler handler = null;

    public PhotoMetaAPI() {}

    public PhotoMetaAPI(String mUrl, RequestParams mParams, ApiSyncHandler mHandler) {

        this.url = mUrl;
        this.params = mParams;
        this.handler = mHandler;
    }

    public void syncObject()    {

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(this.url, this.params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        handler.failed(responseString, throwable);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        handler.success(responseString);
                    }
                }
        );
    }
}
