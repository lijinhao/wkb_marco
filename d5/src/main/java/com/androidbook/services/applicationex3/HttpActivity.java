package com.androidbook.services.applicationex3;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.androidbook.services.applicationex.*;

public class HttpActivity extends Activity
{
    private com.androidbook.services.applicationex.ApplicationEx app;
    private HttpClient httpClient;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        app = (com.androidbook.services.applicationex.ApplicationEx)this.getApplication();
        httpClient = app.getHttpClient();
        getHttpContent();
    }

    public void getHttpContent()
    {
        try {
            HttpGet request = new HttpGet("http://www.google.com/");
            HttpParams params = request.getParams();
            HttpConnectionParams.setSoTimeout(params, 60000);   // 1 minute
            request.setParams(params);
            Log.v("connection timeout", String.valueOf(HttpConnectionParams.getConnectionTimeout(params)));
            Log.v("socket timeout", String.valueOf(HttpConnectionParams.getSoTimeout(params)));
            HttpResponse response = httpClient.execute(request);

            String page=EntityUtils.toString(response.getEntity());
            System.out.println(page);
		} catch (IOException e) {
			// covers:
			//      ClientProtocolException
			//      ConnectTimeoutException
			//      ConnectionPoolTimeoutException
			//      SocketTimeoutException
			e.printStackTrace();
		}
    }
}
