public class Connection {
    private String actual_ip;
    private String username;
    private String password;

    public Connection(){
        this.actual_ip = "192.168.2.101";
        this.username = "user_test";
        this.password = "12345678";
    }

    public String getActual_ip(){
        return actual_ip;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
