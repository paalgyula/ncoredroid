package com.gwsystems.ncoredroid.requests;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.gwsystems.ncoredroid.LoginActivity;
import com.gwsystems.ncoredroid.R;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class LoginCheckRequest extends AsyncTask<Void, Void, Boolean> {
    private LoginActivity loginActivity;

    public LoginCheckRequest(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    protected Boolean doInBackground(Void... voids) {
        HttpClient httpClient = LoginActivity.getHttpClient();
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
            } else if ((responseCode == 301) || (responseCode == 302)) {
                Log.i("ResponseCode", "Redirect: " + responseCode);
                //Toast.makeText(loginActivity, R.string.not_logged_in, Toast.LENGTH_SHORT).show();
                return false;
            } else {
                //Toast.makeText(loginActivity, R.string.not_logged_in, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    protected void onPostExecute(Boolean loggedIn) {
        ProgressDialog dialog = loginActivity.getProgressDialog();
        dialog.setMessage(loginActivity.getString(R.string.logging_in));
        dialog.setCancelable(false);
        dialog.show();
        if (!loggedIn) {
            try {
                if (new LoginRequest(loginActivity).execute().get()) {
                    loginActivity.hideProgressDialog();
                    loginActivity.showFragment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            loginActivity.showFragment();
        }
    }
}