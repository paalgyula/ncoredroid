package com.gwsystems.ncoredroid.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gwsystems.ncoredroid.LoginActivity;
import com.gwsystems.ncoredroid.R;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class LoginRequest extends AsyncTask<CharSequence, Void, Boolean> {
    public static final String DAT_FILENAME = "ncoreconfig.dat";
    private LoginActivity loginActivity;

    public LoginRequest(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    @Override
    protected Boolean doInBackground(CharSequence... args) {
        HttpClient httpClient = LoginActivity.getHttpClient();
        HttpPost httppost = new HttpPost(loginActivity.getString(R.string.ncore_login_url));

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);

            /**
             * set_lang:hu
             submitted:1
             nev:goofyx
             submit:Belépés!
             pass:susegoofyY50027462
             */

            //String username = args[0];
            //String password = args[1];

            nameValuePairs.add(new BasicNameValuePair("submitted", "1"));
            nameValuePairs.add(new BasicNameValuePair("submit", "Belépés!"));

            nameValuePairs.add(new BasicNameValuePair("nev", args[0].toString()));
            nameValuePairs.add(new BasicNameValuePair("pass", args[1].toString()));

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

            // Write login info to file
            FileOutputStream fos = loginActivity.openFileOutput(DAT_FILENAME, Context.MODE_PRIVATE);
            fos.write((args[0].toString() + "\n" + args[1].toString()).getBytes());
            fos.close();
        } catch (ClientProtocolException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        loginActivity.hideProgressDialog();
        loginActivity.showFragment();
    }
}
