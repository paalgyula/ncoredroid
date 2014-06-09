package com.gwsystems.ncoredroid.requests;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.gwsystems.ncoredroid.LoginActivity;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class DownloadRequest extends AsyncTask<CharSequence, Void, Void> {
    private final Activity activity;

    public DownloadRequest(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(CharSequence... args) {
        DefaultHttpClient httpClient = ((DefaultHttpClient) LoginActivity.getHttpClient());
        File externalDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        externalDir.mkdirs();
        try {
            HttpEntity entity = httpClient.execute(new HttpGet(args[0].toString())).getEntity();
            File torrentFile = new File(externalDir, "[nCore]" + args[1] + ".torrent");
            torrentFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(torrentFile);
            fos.write(EntityUtils.toByteArray(entity));
            fos.close();

            try {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(torrentFile), "application/x-bittorrent");
                activity.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
