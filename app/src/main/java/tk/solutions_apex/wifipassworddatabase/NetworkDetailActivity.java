package tk.solutions_apex.wifipassworddatabase;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.cryptonode.jncryptor.AES256JNCryptor;
import org.cryptonode.jncryptor.CryptorException;
import org.cryptonode.jncryptor.JNCryptor;

import java.util.HashMap;

import tk.solutions_apex.wifipassworddatabase.helper.Network;
import tk.solutions_apex.wifipassworddatabase.helper.NetworksDataSource;
import tk.solutions_apex.wifipassworddatabase.helper.SQLiteHandler;

public class NetworkDetailActivity extends AppCompatActivity {
    private NetworksDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final CheckBox showPass = (CheckBox) findViewById(R.id.showPass);
        TextView netName = (TextView) findViewById(R.id.netName);
        final TextView netPass = (TextView) findViewById(R.id.netPass);
        setSupportActionBar(toolbar);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        datasource = new NetworksDataSource(getApplicationContext());
        datasource.open();

        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        final HashMap<String, String> user = db.getUserDetails();

        int netID = getIntent().getExtras().getInt("networkID", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String SSID = datasource.getNetwork(netID).getSSID();
        byte[] dbPassword = datasource.getNetwork(netID).getPassword();

        JNCryptor cryptor = new AES256JNCryptor();

        byte[] password = new byte[0];
        char[] hash = user.get("uid").toCharArray();
        try {
            password = cryptor.decryptData(dbPassword, hash);
        } catch (CryptorException e) {
            // Something went wrong
            Log.e("NetworkDetails", String.valueOf(e.getStackTrace()[0]));
            e.printStackTrace();
        }

        netName.setText(SSID);
        netPass.setText(new String(password));

        findViewById(R.id.showPass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showPass.isChecked()) {
                    netPass.setTransformationMethod(null);
                } else {
                    netPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

}
