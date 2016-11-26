package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterParams {

    @SerializedName("email")
    private String mEmail;

    @SerializedName("account_no")
    private String mAccountNo;

    @SerializedName("contact_no")
    private String mContactNo;

    @SerializedName("verification_code")
    private String mVeficationCode;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("confirm_password")
    private String mConfirmPassword;

    public RegisterParams(String email, String accountNo, String contactNo,
                          String veficationCode, String password, String confirmPassword) {
        mEmail = email;
        mAccountNo = accountNo;
        mContactNo = contactNo;
        mVeficationCode = veficationCode;
        mPassword = password;
        mConfirmPassword = confirmPassword;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getAccountNo() {
        return mAccountNo;
    }

    public void setAccountNo(String accountNo) {
        mAccountNo = accountNo;
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
