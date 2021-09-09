package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    String webInfo = "";
    List<String> celebNames;
    Button button1;

    public void loadImages(){
        Pattern p = Pattern.compile("gridItem_nameLink__3jE6V\">(.*?)</a>");
        Matcher m = p.matcher(webInfo);

        while (m.find()){
            celebNames.add(m.group(1));
        }

        Log.i("WEBPAGE", webInfo);
        Log.i("CELEB NAMES", Arrays.toString(celebNames.toArray()));
    }

    public void nextQuestion(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebNames = new ArrayList<String>();

        WebsiteDownloader downloader = new WebsiteDownloader();
        try {
            webInfo = downloader.execute("https://www.ranker.com/list/forbes-100-most-powerful-celebrities/worlds-richest-people-lists").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loadImages();
        nextQuestion();
    }

    class WebsiteDownloader extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String data = "";
            URL url;
            try {
                url = new URL(strings[0]);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int letter = reader.read();
                while(letter != -1){
                    data += (char) letter;
                    letter = reader.read();
                }
                return data;

            } catch (Exception e) {
                return "FAILED";
            }
        }
    }
}