package tk.solutions_apex.wifipassworddatabase.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mike5 on 2017-01-30.
 */

public class NetworkSQLiteHandler extends SQLiteOpenHelper {

    public static final String TABLE_NETWORKS = "networks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SSID = "ssid";
    public static final String COLUMN_PASS = "password";

    private static final String DATABASE_NAME = "networks.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NETWORKS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SSID
            + " text not null, " + COLUMN_PASS + " text not null);";

    public NetworkSQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NetworkSQLiteHandler.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORKS);
        onCreate(db);
    }
}
