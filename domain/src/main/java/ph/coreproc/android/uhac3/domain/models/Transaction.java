package ph.coreproc.android.uhac3.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class Transaction {

    @SerializedName("ref_no")
    private String mReferenceNumber;

    @SerializedName("source")
    private String mSourceMobileNo;

    // NULLABLE
    @SerializedName("recipient")
    private String mRecipientMobileNo;

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
