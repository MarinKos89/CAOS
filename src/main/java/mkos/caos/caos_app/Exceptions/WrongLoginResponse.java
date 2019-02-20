package mkos.caos.caos_app.Exceptions;

public class WrongLoginResponse {

    private String username;
    private String password;

    public WrongLoginResponse() {
        this.username = "wrong username";
        this.password = "wrong password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
