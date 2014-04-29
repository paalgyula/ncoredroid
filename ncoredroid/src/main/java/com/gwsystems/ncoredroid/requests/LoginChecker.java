package com.gwsystems.ncoredroid.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.gwsystems.ncoredroid.MainActivity;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class LoginChecker extends AsyncTask<Void, Void, Boolean> {
    protected Boolean doInBackground(Void... voids) {
        HttpClient httpClient = MainActivity.getHttpClient();
        HttpGet httpGet = new HttpGet("https://ncore.cc/letoltes.php");

        try {
            HttpResponse response = httpClient.execute(httpGet);
            Integer responseCode = response.getStatusLine().getStatusCode();

            for (Header header : response.getAllHeaders()) {
                Log.i("Headers", header.getName() + " - " + header.getValue());
            }

            Log.wtf("ResponseCode", "" + responseCode);

            if (responseCode == 200) {
                return true;
            } else if (responseCode == 301) {
                Log.i("ResponseCode", "Redirect: " + responseCode);
                return false;
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    protected void onPostExecute(Boolean loggedIn) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}