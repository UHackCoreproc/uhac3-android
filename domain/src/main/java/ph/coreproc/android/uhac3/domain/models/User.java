package ph.coreproc.android.uhac3.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 01/10/2016.
 */
public class User {

    @SerializedName("id")
    private long mUserId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("mobileNumber")
    private MobileNumberVerification mMobileNumberVerification;

    private Authorization mAuthorization;

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public Authorization getAuthorization() {
        return mAuthorization;
    }

    public void setAuthorization(Authorization authorization) {
        mAuthorization = authorization;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
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

    public String getMobileNumber() {
        return mMobileNumberVerification.getMobileNumber();
    }

    public void setMobileNumberVerification(MobileNumberVerification mobileNumberVerification) {
        mMobileNumberVerification = mobileNumberVerification;
    }
}
