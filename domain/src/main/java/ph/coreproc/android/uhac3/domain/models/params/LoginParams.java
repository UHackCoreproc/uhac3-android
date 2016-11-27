package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 05/11/2016.
 */

public class LoginParams {

    @SerializedName("email")
    private String mUsername;

    @SerializedName("password")
    private String mPassword;

    public LoginParams(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
