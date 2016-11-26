package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class VerifyMobileNumberParams {

    @SerializedName("mobile_no")
    private String mMobileNumber;

    public VerifyMobileNumberParams(String mobileNumber) {
        mMobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mMobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        mMobileNumber = mobileNumber;
    }
}
