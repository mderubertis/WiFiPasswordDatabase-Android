package tk.solutions_apex.wifipassworddatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tk.solutions_apex.wifipassworddatabase.helper.Network;
import tk.solutions_apex.wifipassworddatabase.helper.NetworksDataSource;

public class NewNetworkActivity extends AppCompatActivity {
    private NetworksDataSource datasource;
    EditText mSSID;
    EditText mPassword;
    Button mAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_network);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAdd = (Button) findViewById(R.id.add_network_button);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datasource = new NetworksDataSource(getApplicationContext());
                datasource.open();
                Network network = new Network();
                mSSID   = (EditText)findViewById(R.id.ssid);
                mPassword   = (EditText)findViewById(R.id.password);

                // save the new comment to the database
                network = datasource.createNetwork(mSSID.getText().toString(), mPassword.getText().toString());
                datasource.close();
                Toast.makeText(getApplicationContext(), "Network added.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewNetworkActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}
