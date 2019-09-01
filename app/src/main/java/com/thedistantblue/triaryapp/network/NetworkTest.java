package com.thedistantblue.triaryapp.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.thedistantblue.triaryapp.R;
import com.thedistantblue.triaryapp.database.DAO;
import com.thedistantblue.triaryapp.entities.Training;
import com.thedistantblue.triaryapp.entities.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkTest extends AppCompatActivity {
    private class RequestBulder extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                /*
                //HTTP GET EXAMPLE
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/training/all")
                        .get()
                        .build();
                Response response = httpClient.newCall(request).execute();
                return response.body().string();
                */

                //HTTP PATCH EXAMPLE
                /*
                OkHttpClient httpClient = new OkHttpClient();
                RequestBody requestBody =
                        RequestBody.create(MediaType.get("application/json"), "{123}");
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8080/training/")
                        .patch(requestBody)
                        .build();
                Response response = httpClient.newCall(request).execute();
                return String.valueOf(response.code());
                */

                //URL url = new URL("http://10.0.2.2:8080/training/all");

                URL url = new URL("http://10.0.2.2:8080/training/add");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");

                Gson gson = new Gson();
                User user = new User(1);
                List<Training> trainingList = DAO.get(getApplicationContext()).getTrainingsList(user);
                String trainingString = gson.toJson(trainingList);

                Log.d("JSON from training: ", trainingString);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                bw.write(trainingString);
                bw.flush();

                //BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //String json = br.readLine();
                return String.valueOf(connection.getResponseCode());
                //String json = String.valueOf(connection.getResponseCode());
                //return json;

            } catch (Exception exc) {
                System.out.println(exc);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String param) {
            Log.d("JSON: ", param);
            textView.setText(param);
        }
    }

    TextView textView;
    RequestBulder getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_data_layout);
        textView = findViewById(R.id.json);
        getData = new RequestBulder();
        getData.execute("blabla");
    }
}
