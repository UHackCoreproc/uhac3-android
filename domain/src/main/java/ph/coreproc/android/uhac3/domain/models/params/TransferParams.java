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
    }
}
