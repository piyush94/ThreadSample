package com.example.piyush.threadsample;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import net.steamcrafted.loadtoast.LoadToast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush on 28-05-2015.
 */
public class PostData extends AsyncTask<String, String, String> {

    LoadToast toast;
    private Context mContext;
    private String addressIp = null;

    PostData(Context context, String address){

        mContext = context;
        addressIp = address;

    }

    @Override
    protected void onPreExecute() {

        toast = new LoadToast(mContext);
        toast.setText("PUSHING");
        toast.setBackgroundColor(Color.WHITE).setProgressColor(Color.BLACK).setTextColor(Color.BLACK);
        toast.show();

    }

    @Override
    protected void onPostExecute(String s) {

        toast.success();

    }

    @Override
    protected String doInBackground(String... params) {

        JSONObject jb = new JSONObject();
        try {
            jb.put("m1",5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try
        {

            List<NameValuePair> l = new ArrayList<NameValuePair>(1);
            l.add(new BasicNameValuePair("json", jb.toString()));
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://"+addressIp+"/dummytest.php");
            httpPost.setEntity(new UrlEncodedFormEntity(l));
            httpClient.execute(httpPost);

        }
        catch (Exception e) {

        }

        return null;
    }
}
