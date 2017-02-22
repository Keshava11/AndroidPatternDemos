package in.blogspot.ravinishad.stylemvplegacy.login.modals;

import com.google.gson.annotations.Expose;

/**
 * Created by ravi on 2/2/17.
 */
public class LoginRequest {

    public String getEmailId() {
        return email;
    }

    public LoginRequest setEmailId(String emaildId) {
        this.email = emaildId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @Expose
    private String email;
    @Expose
    private String password;

}
