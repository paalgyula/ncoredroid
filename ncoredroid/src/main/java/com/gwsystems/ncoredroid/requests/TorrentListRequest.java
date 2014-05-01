package com.gwsystems.ncoredroid.requests;

import android.os.AsyncTask;
import android.util.Log;

import com.gwsystems.ncoredroid.CustomProgressDialog;
import com.gwsystems.ncoredroid.LoginActivity;
import com.gwsystems.ncoredroid.adapters.TorrentListAdapter;
import com.gwsystems.ncoredroid.entity.TorrentObject;
import com.gwsystems.ncoredroid.entity.TorrentUploader;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paalgyula on 2014.04.27..
 */
public class TorrentListRequest extends AsyncTask<String, Void, List<TorrentObject>> {

    private TorrentListHolder torrentListHolder;

    public TorrentListRequest(TorrentListHolder torrentListHolder) {
        this.torrentListHolder = torrentListHolder;
    }

    @Override
    protected List<TorrentObject> doInBackground(String... args) {
        HttpClient httpClient = LoginActivity.getHttpClient();
        HttpPost httpPost = new HttpPost("https://ncore.cc/torrents.php");

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        /**
         * mire:
         miben:name
         tipus:kivalasztottak_kozott
         submit.x:34
         submit.y:14
         submit:Ok
         tags:
         */

        if (args.length > 0)
            nameValuePairs.add(new BasicNameValuePair("mire", args[0]));

        nameValuePairs.add(new BasicNameValuePair("miben", "name"));
        nameValuePairs.add(new BasicNameValuePair("tipus", "all_own"));
        nameValuePairs.add(new BasicNameValuePair("submit.x", "34"));
        nameValuePairs.add(new BasicNameValuePair("submit.y", "14"));
        nameValuePairs.add(new BasicNameValuePair("submit", "Ok"));
        nameValuePairs.add(new BasicNameValuePair("tags", ""));

        LinkedList<TorrentObject> torrentObjects = new LinkedList<TorrentObject>();
        try {
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.3) Gecko/20090824 Firefox/3.5.3");
            httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpPost.setHeader("Accept-Charset", "utf-8;q=0.7,*;q=0.7");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            String responseString = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);

            Log.i("TorrentListRequest", "Executed HTTP Post");

            Document doc = Jsoup.parse(responseString);
            Elements torrents = doc.select("#main_tartalom .box_torrent");

            for (Element torrentElement : torrents) {
                TorrentObject torrentObject = new TorrentObject();

                // Torrent name, link
                Element linkElement = this.getElement(torrentElement, ".tabla_szoveg a");

                if (linkElement != null) {
                    torrentObject.setName(linkElement.attr("title"));
                    torrentObject.setLink(linkElement.attr("href"));
                }

                // Torrent size
                Element sizeElement = this.getElement(torrentElement, ".box_meret2");
                if (sizeElement != null)
                    torrentObject.setSize(sizeElement.text());

                Element d2Element = this.getElement(torrentElement, ".box_d2");
                if (sizeElement != null)
                    torrentObject.setDownloaded(d2Element.text());

                Element s2Element = this.getElement(torrentElement, ".box_s2");
                if (sizeElement != null)
                    torrentObject.setSeed(s2Element.text());

                Element l2Element = this.getElement(torrentElement, ".box_l2");
                if (sizeElement != null)
                    torrentObject.setLeech(l2Element.text());

                Element categoryElement = this.getElement(torrentElement, ".box_alap_img a");
                if (categoryElement != null)
                    torrentObject.setCategory(categoryElement.attr("href").replace("/torrents.php?tipus=", ""));

                // Torrent feltolto
                Element uploaderElement = this.getElement(torrentElement, ".box_feltolto2 a");
                if ( uploaderElement != null ) {
                    Element spanElement = this.getElement(uploaderElement, "span");
                    torrentObject.setTorrentUploader(new TorrentUploader(
                            spanElement.text(),
                            uploaderElement.attr("href").replace("profile.php?id=", ""),
                            spanElement.attr("class")
                    ));

                }

                torrentObjects.addLast(torrentObject);
            }

            Log.i("TorrentListRequest", "Parsed objects...");

            return torrentObjects;
        } catch (Exception e) {
            return torrentObjects;
        }
    }

    @Override
    protected void onPostExecute(List<TorrentObject> torrentObjects) {
        Log.wtf("TorrentList Size", "TorrentList Size: " + torrentObjects.size());
        for (Iterator<TorrentObject> itr = torrentObjects.iterator(); itr.hasNext(); ) {
            TorrentObject torrentObject = itr.next();
            torrentListHolder.getTorrentListAdapter().add(torrentObject);
        }

        torrentListHolder.getProgressDialog().hide();
    }

    private Element getElement(Element torrentElement, String selector) {
        Elements textElements = torrentElement.select(selector);
        Iterator<Element> iterator = textElements.iterator();

        if (iterator.hasNext()) {
            Element linkElement = iterator.next();
            return linkElement;
        } else {
            Log.e("ElementFinder", "Element not found: " + selector );
            System.err.println(torrentElement);
            return null;
        }
    }

    public interface TorrentListHolder {
        TorrentListAdapter getTorrentListAdapter();
        CustomProgressDialog getProgressDialog();
    }
}
