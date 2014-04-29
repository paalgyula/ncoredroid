package com.gwsystems.ncoredroid.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.gwsystems.ncoredroid.MainActivity;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class LoginRequest extends AsyncTask<Void, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        HttpClient httpClient = MainActivity.getHttpClient();
        HttpPost httppost = new HttpPost("https://ncore.cc/login.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            /**
             * set_lang:hu
             submitted:1
             nev:goofyx
             submit:Belépés!
             pass:susegoofyY50027462
             */

            nameValuePairs.add(new BasicNameValuePair("submitted", "1"));
            nameValuePairs.add(new BasicNameValuePair("nev", "goofyx"));
            nameValuePairs.add(new BasicNameValuePair("submit", "Belépés!"));
            nameValuePairs.add(new BasicNameValuePair("pass", "susegoofyY50027462"));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpClient.execute(httppost);

            Log.wtf("Received entity:", EntityUtils.toString(response.getEntity()));

            for (Header header : response.getAllHeaders()) {
                if (header.getName().equals("location")) {
                    Log.wtf("Redirected to:", header.getValue());
                }
            }
            Log.wtf("Login Response", "" + response.getStatusLine().getStatusCode());
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
