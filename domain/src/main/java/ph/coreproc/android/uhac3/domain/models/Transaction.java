package ph.coreproc.android.uhac3.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class Transaction {

    @SerializedName("ref_no")
    private String mReferenceNumber;

    @SerializedName("source")
    private Account mSourceAccount;

    // NULLABLE
    @SerializedName("recipient")
    private Account mRecipientAccount;

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

    public Account getSourceAccount() {
        return mSourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        mSourceAccount = sourceAccount;
    }

    public Account getRecipientAccount() {
        return mRecipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        mRecipientAccount = recipientAccount;
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
