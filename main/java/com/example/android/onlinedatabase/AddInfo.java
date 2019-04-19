package com.example.android.onlinedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AddInfo extends AppCompatActivity {
    EditText app_name,app_email,app_mobile;
    String name,email,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        app_name=(EditText) findViewById(R.id.uName);
        app_email=(EditText) findViewById(R.id.emailid);
        app_mobile=(EditText) findViewById(R.id.mobileno);
    }

    public void saveInfo(View view){
        name=app_name.getText().toString();
        email=app_email.getText().toString();
        mobile=app_mobile.getText().toString();
        BackgroundTask backgroundTask =new BackgroundTask(this);
        backgroundTask.execute(name,email,mobile);
        finish();
    }
}

class BackgroundTask extends AsyncTask<String, Void, String> {

    String add_url;
    Context context;
    BackgroundTask(Context context)
    {
        this.context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        add_url="http://bested-anchor.000webhostapp.com/add_info.php";

    }

    @Override
    protected String doInBackground(String... strings) {
            String name = strings[0];
            String email=strings[1];
            String mobile=strings[2];
            try {
                URL url = new URL(add_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8") + "=" +URLEncoder.encode(name,"UTF-8") + "&" +
                        URLEncoder.encode("email","UTF-8") + "=" +URLEncoder.encode(email,"UTF-8") + "&" +
                        URLEncoder.encode("mobile","UTF-8") + "=" +URLEncoder.encode(mobile,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                httpURLConnection.disconnect();
                return "Data inserted.";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();

    }
}
