package tk.solutions_apex.wifipassworddatabase.helper;

/**
 * Created by mike5 on 2017-01-30.
 */

public class Network {
    private long id;
    private String ssid;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSSID() {
        return ssid;
    }

    public void setSSID(String ssid) {
        this.ssid = ssid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*// Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }*/
}
