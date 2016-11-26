package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 27/11/2016.
 */

public class RedeemCouponParams {

    @SerializedName("source_mobile_no")
    private String mSourceMobileNo;

    @SerializedName("recipient_mobile_no")
    private String mRecipientMobileNo;

    @SerializedName("coupon_code")
    private String mCouponCode;

    public RedeemCouponParams(String sourceMobileNo, String recipientMobileNo, String couponCode) {
        mSourceMobileNo = sourceMobileNo;
        mRecipientMobileNo = recipientMobileNo;
        mCouponCode = couponCode;
    }

    public String getSourceMobileNo() {
        return mSourceMobileNo;
    }

    public void setSourceMobileNo(String sourceMobileNo) {
        mSourceMobileNo = sourceMobileNo;
    }

    public String getRecipientMobileNo() {
        return mRecipientMobileNo;
    }

    public void setRecipientMobileNo(String recipientMobileNo) {
        mRecipientMobileNo = recipientMobileNo;
    }

    public String getCouponCode() {
        return mCouponCode;
    }

    public void setCouponCode(String couponCode) {
        mCouponCode = couponCode;
    }
}
