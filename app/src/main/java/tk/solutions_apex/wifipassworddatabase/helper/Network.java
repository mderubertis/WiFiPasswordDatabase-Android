package tk.solutions_apex.wifipassworddatabase.helper;

/**
 * Created by mike5 on 2017-01-30.
 */

public class Network {
    private long id;
    private String ssid;
    private byte[] password;

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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    /*// Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }*/
}
