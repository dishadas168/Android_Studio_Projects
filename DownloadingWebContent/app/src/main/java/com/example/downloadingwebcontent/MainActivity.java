package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    class WebsiteDownloader extends AsyncTask<String,Void ,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection = null;

            try {
                //Convert string to url object
                url = new URL(strings[0]);
                //Use this url object to open the connection
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //Get data from connection about html code
                InputStream in = httpURLConnection.getInputStream();
                //Use reader to read this html code char by char
                InputStreamReader reader = new InputStreamReader(in);

                int data =  reader.read();

                while(data != -1){
                    result += (char) data;
                    data = reader.read();
                }
                return result;
            } catch (Exception e){
                Log.i("ERROR", e.toString());
                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String result = null;
        WebsiteDownloader websiteDownloader = new WebsiteDownloader();

        try{
            result = websiteDownloader.execute("http://www.zappycode.com").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("Result of downloader", result);

    }
}