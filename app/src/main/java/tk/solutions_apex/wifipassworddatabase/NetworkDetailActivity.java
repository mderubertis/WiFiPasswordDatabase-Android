package tk.solutions_apex.wifipassworddatabase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tk.solutions_apex.wifipassworddatabase.helper.Network;
import tk.solutions_apex.wifipassworddatabase.helper.NetworksDataSource;

public class NetworkDetailActivity extends AppCompatActivity {
    private NetworksDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        datasource = new NetworksDataSource(getApplicationContext());
        datasource.open();

        int netID = getIntent().getExtras().getInt("networkID", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d("netDetails", datasource.getNetwork(netID).toString());
    }

}
