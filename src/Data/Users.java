package Data;

public class Users {

    private String user_name;
    private String user_IP;
    private String user_port;

    public Users(String user_name, String user_IP, String user_port) {
        this.user_name = user_name;
        this.user_IP = user_IP;
        this.user_port = user_port;
    }

    public Users(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {

        return user_name;

    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_IP() {
        return user_IP;
    }

    public void setUser_IP(String user_IP) {
        this.user_IP = user_IP;
    }

    public String getUser_port() {
        return user_port;
    }

    public void setUser_port(String user_port) {
        this.user_port = user_port;
    }
}
