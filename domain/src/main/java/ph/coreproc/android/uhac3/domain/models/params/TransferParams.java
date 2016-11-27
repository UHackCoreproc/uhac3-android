package ph.coreproc.android.uhac3.domain.models.params;

import com.google.gson.annotations.SerializedName;

import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public class TransferParams {

    @SerializedName("source")
    private Account mSourceAccount;

    @SerializedName("recipient")
    private Account mRecipientAccount;

    @SerializedName("source_account_id")
    private String mSourceAccountId;

    @SerializedName("target_account_id")
    private String mTargetAccountId;

    @SerializedName("mobile_number")
    private String mMobileNumber;

    @SerializedName("amount")
    private double mAmount;

    @SerializedName("remarks")
    private String mRemarks;

    public TransferParams(Account sourceAccount, Account recipientAccount,
                          String mobileNumber, double amount, String remarks) {
        mSourceAccount = sourceAccount;
        mRecipientAccount = recipientAccount;
        mMobileNumber = mobileNumber;
        mAmount = amount;
        mRemarks = remarks;

        mSourceAccountId = mSourceAccount != null ? mSourceAccount.getId() + "" : "";
        mTargetAccountId = mRecipientAccount != null ? mRecipientAccount.getId() + "" : "";
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

    public String getSourceAccountId() {
        return mSourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        mSourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return mTargetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        mTargetAccountId = targetAccountId;
    }

    public String getMobileNumber() {
        return mMobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        mMobileNumber = mobileNumber;
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
}
