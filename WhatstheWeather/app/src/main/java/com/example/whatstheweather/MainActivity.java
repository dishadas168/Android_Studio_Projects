package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String city = "";
    String weatherString = "";
    TextView resultText;

    public void getWeather(View view){
        EditText cityName = (EditText) findViewById(R.id.cityName);
        city = cityName.getText().toString();
        if(city.isEmpty()) Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
        else{
            DownloadWeatherData weatherData = new DownloadWeatherData();
            try {
                weatherString = weatherData.execute("https://samples.openweathermap.org/data/2.5/weather?q={" + city + "}&appid={11cbbaeb38499a8ca38a1b9a6ce7d1ea}").get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.i("STRING", weatherString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView) findViewById(R.id.textViewInfo);
    }

    class DownloadWeatherData extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String webInfo = "";
            HttpURLConnection connection ;

            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    webInfo += (char) data;
                    data = reader.read();
                }
                return  webInfo;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "FAILED";
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            try {
                JSONObject jsonObject = new JSONObject(string);
                String weatherInfo = jsonObject.getString("weather");
                JSONArray arr = new JSONArray((weatherInfo));
                String message = "";

                for(int i=0; i < arr.length(); i++){
                    JSONObject jsonPart = arr.getJSONObject(i);

                    String main = jsonPart.getString("main");
                    String description = jsonPart.getString("description");

                    if(!main.equals("") && !description.equals("")){
                        message += main + ": " + description;
                    }
                }

                if(!message.equals("")){
                    resultText.setText(message);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}