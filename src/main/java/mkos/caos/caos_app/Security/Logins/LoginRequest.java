package mkos.caos.caos_app.Security.Logins;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "Enter username")
    private String username;

    @NotNull(message = "Enter password")
    private String password;

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
