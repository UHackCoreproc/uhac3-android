package ph.coreproc.android.uhac3.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class Transaction {

    @SerializedName("reference_number")
    private String mReferenceNumber;

    @SerializedName("source")
    private String mSourceMobileNo;

    // NULLABLE
    @SerializedName("recipient")
    private String mRecipientMobileNo;

    @SerializedName("sourceAccount")
    private User mSourceUser;

    @SerializedName("targetAccount")
    private User mRecipientUser;

    @SerializedName("amount")
    private double mAmount;

    @SerializedName("remarks")
    private String mRemarks;

    @SerializedName("status")
    private String mStatus;

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        mReferenceNumber = referenceNumber;
    }

    public String getSourceMobileNo() {
        if (mSourceUser == null) {
            return "";
        }
        return mSourceUser.getMobileNumber();
    }

    public void setSourceMobileNo(String sourceMobileNo) {
        mSourceMobileNo = sourceMobileNo;
    }

    public String getRecipientMobileNo() {
        if (mRecipientUser == null) {
            return "";
        }
        return mRecipientUser.getMobileNumber();
    }

    public void setRecipientMobileNo(String recipientMobileNo) {
        mRecipientMobileNo = recipientMobileNo;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public String getRemarks() {
        return mRemarks;
    }

    public void setRemarks(String remarks) {
        mRemarks = remarks;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
