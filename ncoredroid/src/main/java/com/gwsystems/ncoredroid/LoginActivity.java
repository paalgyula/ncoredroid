package com.gwsystems.ncoredroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.gwsystems.ncoredroid.requests.LoginRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import java.net.URI;

public class LoginActivity extends Activity {

    private static final HttpClient httpClient = new DefaultHttpClient();
    private ProgressDialog progressDialog;


    public static HttpClient getHttpClient() {
        return LoginActivity.httpClient;
    }

    public LoginActivity() {
        ((DefaultHttpClient) httpClient).setRedirectHandler(new RedirectHandler() {
            @Override
            public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
                return false;
            }

            @Override
            public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
                return null;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.progressDialog = new CustomProgressDialog(this);
        setContentView(R.layout.activity_login);

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Bejelentkezés folyamatban...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                new LoginRequest(LoginActivity.this).execute();
            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void showFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void hideProgressDialog() {
        progressDialog.hide();
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
