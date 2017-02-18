package tk.solutions_apex.wifipassworddatabase.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mike5 on 2017-01-30.
 */

public class NetworksDataSource {
    // Database fields
    private SQLiteDatabase database;
    private NetworkSQLiteHandler dbHelper;
    private String[] allColumns = { NetworkSQLiteHandler.COLUMN_ID,
            NetworkSQLiteHandler.COLUMN_SSID, NetworkSQLiteHandler.COLUMN_PASS };

    public NetworksDataSource(Context context) {
        dbHelper = new NetworkSQLiteHandler(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Network createNetwork(String ssid, byte[] password) {
        ContentValues values = new ContentValues();
        values.put(NetworkSQLiteHandler.COLUMN_SSID, ssid);
        values.put(NetworkSQLiteHandler.COLUMN_PASS, password);
        long insertId = database.insert(NetworkSQLiteHandler.TABLE_NETWORKS, null,
                values);
        Cursor cursor = database.query(NetworkSQLiteHandler.TABLE_NETWORKS,
                allColumns, NetworkSQLiteHandler.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Network newNetwork = cursorToNetwork(cursor);
        cursor.close();
        return newNetwork;
    }

    public void deleteNetwork(Network network) {
        long id = network.getId();
        System.out.println("Network deleted with id: " + id);
        database.delete(NetworkSQLiteHandler.TABLE_NETWORKS, NetworkSQLiteHandler.COLUMN_ID
                + " = " + id, null);
    }

    public List<Network> getAllNetworks() {
        List<Network> networks = new ArrayList<Network>();

        Cursor cursor = database.query(NetworkSQLiteHandler.TABLE_NETWORKS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Network network = cursorToNetwork(cursor);
            networks.add(network);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return networks;
    }

    public List<String> getAllNetworkNames() {
        List<String> networksSSID = new ArrayList<>();

        Cursor cursor = database.query(NetworkSQLiteHandler.TABLE_NETWORKS,
                new String[]{NetworkSQLiteHandler.COLUMN_ID, NetworkSQLiteHandler.COLUMN_SSID, NetworkSQLiteHandler.COLUMN_PASS}, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Network network = cursorToNetwork(cursor);
            networksSSID.add(network.getSSID());
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return networksSSID;
    }

    public Network getNetwork(int id) {

        Cursor cursor = database.query(NetworkSQLiteHandler.TABLE_NETWORKS,
                new String[]{NetworkSQLiteHandler.COLUMN_ID, NetworkSQLiteHandler.COLUMN_SSID, NetworkSQLiteHandler.COLUMN_PASS}, null, null, null, null, null);

        cursor.moveToPosition(id);
        Network network = cursorToNetwork(cursor);

        // make sure to close the cursor
        cursor.close();
        return network;
    }

    private Network cursorToNetwork(Cursor cursor) {
        Network network = new Network();
        network.setId(cursor.getLong(0));
        network.setSSID(cursor.getString(1));
        network.setPassword(cursor.getBlob(2));
        return network;
    }
}
