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
import android.widget.TextView;

import com.gwsystems.ncoredroid.requests.LoginRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class LoginActivity extends Activity {

    private static final HttpClient httpClient = new DefaultHttpClient();
    private ProgressDialog progressDialog;


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

    public static HttpClient getHttpClient() {
        return LoginActivity.httpClient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.progressDialog = new CustomProgressDialog(this);
        setContentView(R.layout.activity_login);

        final TextView usernameField = (TextView) findViewById(R.id.username);
        final TextView passwordField = (TextView) findViewById(R.id.password);

        try {
            FileInputStream fis = openFileInput(LoginRequest.DAT_FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            usernameField.setText(reader.readLine());
            passwordField.setText(reader.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Bejelentkezés folyamatban...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                new LoginRequest(LoginActivity.this).execute(usernameField.getText(), passwordField.getText());
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

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }
}
