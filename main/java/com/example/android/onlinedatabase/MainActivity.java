package com.example.android.onlinedatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView t;
    Button b1 , b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.add);
        b2 = (Button) findViewById(R.id.view);

        t = (TextView) findViewById(R.id.nonetwork);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            t.setVisibility(View.INVISIBLE);
        }
        else
        {
            b1.setEnabled(false);
            b2.setEnabled(false);
        }
    }

public void addContacts(View view){
        startActivity(new Intent(this,AddInfo.class));
}
}
