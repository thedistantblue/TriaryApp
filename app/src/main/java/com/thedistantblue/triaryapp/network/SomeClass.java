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
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.TreeMap;

public class SomeClass extends AppCompatActivity {
    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL("http://10.0.2.2:8080/training/add"); // разные адреса, поэтому не берет
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                Gson gson = new Gson();
                User user = new User(1);
                List<Training> trainingList = DAO.get(getApplicationContext()).getTrainingsList(user);
                String trainingString = gson.toJson(trainingList.get(0));
                Log.d("JSON from training: ", trainingString);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                bw.write(trainingString);
                bw.flush();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String json = br.readLine();
                return json;
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
    GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_data_layout);
        textView = findViewById(R.id.json);
        getData = new GetData();
        getData.execute("blabla");
    }
}
