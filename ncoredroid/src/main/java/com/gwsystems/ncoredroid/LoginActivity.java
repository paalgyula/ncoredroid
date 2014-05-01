package com.gwsystems.ncoredroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.gwsystems.ncoredroid.adapters.TorrentListAdapter;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.requests.LoginCheckRequest;
import com.gwsystems.ncoredroid.requests.LoginRequest;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.RedirectHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

import java.net.URI;
import java.util.ArrayList;

public class LoginActivity extends ActionBarActivity {

    private static final HttpClient httpClient = new DefaultHttpClient();
    private TorrentListAdapter torrentListAdapter;
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setupUI();

        Button loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginRequest(LoginActivity.this).execute();
            }
        });

        new LoginCheckRequest(this).execute();
    }

    private void setupUI() {
        this.torrentListAdapter = new TorrentListAdapter(this, new ArrayList<TorrentObject>());
        ListView listView = (ListView) findViewById(R.id.torrentListView);
        listView.setAdapter(this.torrentListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showFragment();
            return true;
        } else if (id == R.id.action_torrents) {
            Log.i("MenuSelect", "Torrent item selected");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showFragment() {
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.torrentListView, TorrentListFragment.newInstance("", ""));
        fragmentTransaction.commit();*/

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void showProgressDialog(String messaage) {
        progressDialog.setCancelable(true);
        progressDialog.setMessage(messaage);
        progressDialog.show();
    }

    public void hideProgressDialog() {
        progressDialog.hide();
    }

    public TorrentListAdapter getTorrentListAdapter() {
        return torrentListAdapter;
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
