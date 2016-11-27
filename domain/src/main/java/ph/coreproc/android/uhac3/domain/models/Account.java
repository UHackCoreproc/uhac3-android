package ph.coreproc.android.uhac3.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 26/11/2016.
 */

public class Account {

    @SerializedName("id")
    private Long mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("account_number")
    private String mAccountNumber;

    @SerializedName("accountType")
    private AccountType mAccountType;

    @SerializedName("balance")
    private Double mBalance;

    public Account() {
    }

    public Account(String title, String description,
                   String accountNumber, AccountType accountType) {
        mId = null;
        mTitle = title;
        mDescription = description;
        mAccountNumber = accountNumber;
        mAccountType = accountType;
        mBalance = null;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAccountNumber() {
        return mAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        mAccountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return mAccountType;
    }

    public void setAccountType(AccountType accountType) {
        mAccountType = accountType;
    }

    public Double getBalance() {
        return mBalance;
    }

    public void setBalance(Double balance) {
        mBalance = balance;
    }
}
