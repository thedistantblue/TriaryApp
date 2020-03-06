package com.thedistantblue.triaryapp.network;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static final String EXCHANGE_NAME = "testExchange1";
    private static final String QUEUE_NAME = "testQueue1";

    public String toPrettyJson(String json) {
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jsonArray);
    }

    public String authUser(URL url) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bw.write("username=user1&password=password1");
            bw.flush();
            bw.close();
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            List<String> cookiesHeader = headerFields.get("Set-Cookie");
            String cookie = cookiesHeader.get(0);
            String[] data = cookie.split(";", 2);
            //return data[0] + "\n" + data[1];
            return cookie;
            //return String.valueOf(connection.getResponseCode());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return "fail";
    }

    public String getData(URL url, String cookie) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", cookie);
            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String json = br.readLine();
                String prettyJson = toPrettyJson(json);
                Log.d("pretty json", prettyJson);
                Gson gson = new Gson();
                List<Training> trainingList = Arrays.asList(gson.fromJson(prettyJson, Training[].class));
                Log.d("parsed data from server", String.valueOf(trainingList.size()));
                return prettyJson;
            } else {
                return String.valueOf(connection.getResponseCode());
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return "";
    }

    public String sendData(URL url) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.0.2.2");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {


            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            channel.queueDeclare(QUEUE_NAME,
                                 false,
                                 false,
                                 false,
                                 null);

            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "test.q1");

            String message = "Hello, world!";

            channel.basicPublish(EXCHANGE_NAME, "test.q1", null, message.getBytes());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
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
            return String.valueOf(connection.getResponseCode());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return "";
    }

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
                switch (params[0]) {
                    case "send":
                        return sendData(new URL("http://10.0.2.2:8080/training/add"));
                    case "get":
                        return getData(new URL("http://10.0.2.2:8080/training/all"), params[1]);
                    case "auth":
                        return authUser(new URL("http://10.0.2.2:8080/login"));
                }
            } catch (Exception exc) {
                System.out.println(exc);
            }
            return "";
        }

        @Override
        protected void onPostExecute(String param) {
            Log.d("JSON: ", param);
            mJsonTextView.setText(param);
            cookie = param;
            this.cancel(true);
        }
    }

    TextView mJsonTextView;
    Button mAuthButton;
    Button mSendData;
    Button mGetData;
    RequestBulder getData;
    String cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_data_layout);
        mJsonTextView = findViewById(R.id.data_text_view);
        mJsonTextView.setMovementMethod(new ScrollingMovementMethod());

        mSendData = findViewById(R.id.send_button);
        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData = new RequestBulder();
                getData.execute("send");
            }
        });

        mGetData = findViewById(R.id.get_button);
        mGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData = new RequestBulder();
                getData.execute("get", cookie);
            }
        });

        mAuthButton = findViewById(R.id.auth_button);
        mAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData = new RequestBulder();
                getData.execute("auth");
            }
        });
    }
}
