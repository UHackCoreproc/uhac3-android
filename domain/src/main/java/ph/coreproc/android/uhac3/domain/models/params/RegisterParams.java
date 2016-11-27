package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterParams {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("contact_no")
    private String mContactNo;

    @SerializedName("verification_code")
    private String mVeficationCode;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("confirm_password")
    private String mConfirmPassword;

    public RegisterParams(String email, String contactNo, String veficationCode,
                          String firstName, String lastName,
                          String password, String confirmPassword) {
        mEmail = email;
        mContactNo = contactNo;
        mVeficationCode = veficationCode;
        mFirstName = firstName;
        mLastName = lastName;
        mPassword = password;
        mConfirmPassword = confirmPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getContactNo() {
        return mContactNo;
    }

    public void setContactNo(String contactNo) {
        mContactNo = contactNo;
    }

    public String getVeficationCode() {
        return mVeficationCode;
    }

    public void setVeficationCode(String veficationCode) {
        mVeficationCode = veficationCode;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getConfirmPassword() {
        return mConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        mConfirmPassword = confirmPassword;
    }
}
