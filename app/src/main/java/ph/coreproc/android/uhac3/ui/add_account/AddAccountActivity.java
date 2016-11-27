package ph.coreproc.android.uhac3.ui.add_account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.AccountType;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;
import ph.coreproc.android.uhac3.ui.redeem_coupon.RedeemCouponActivity;

/**
 * Created by johneris on 27/11/2016.
 */

public class AddAccountActivity extends BaseActivity implements AddAccountView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddAccountActivity.class);
        return intent;
    }

    @BindView(R.id.titleEditText)
    EditText mTitleEditText;

    @BindView(R.id.accountNoEditText)
    EditText mAccountNoEditText;

    @BindView(R.id.accountTypeEditText)
    EditText mAccountTypeEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @Inject
    AddAccountPresenter mAddAccountPresenter;

    @Nullable
    AccountType mAccountType;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mAddAccountPresenter.setAddAccountView(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        mAddAccountPresenter.setAddAccountView(null);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@link RedeemCouponActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_title_add_account), true);
        mAccountTypeEditText.setFocusable(false);
        mAccountTypeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAccountTypeDialog();
            }
        });
    }

    private void showAccountTypeDialog() {
        final List<AccountType> accountTypeList = new ArrayList<>();
        accountTypeList.add(new AccountType(1, "Bank Account"));
        accountTypeList.add(new AccountType(2, "Debit / Credit Card"));
        List<String> accountTypeStringList = new ArrayList<>();
        for (int i = 0; i < accountTypeList.size(); i++) {
            accountTypeStringList.add(accountTypeList.get(i).getName());
        }
        final String[] accountArray = accountTypeStringList.toArray(new String[accountTypeList.size()]);
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setItems(accountArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAccountType = accountTypeList.get(which);
                        mAccountTypeEditText.setText(mAccountType.getName());
                    }
                }).create();
        alertDialog.setTitle("Choose Account Type");
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @OnClick(R.id.addAccountButton)
    public void onAddAccountClicked() {
        String title = mTitleEditText.getText().toString();
        String accountNo = mAccountNoEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();

        String errorMessages = "";
        if (title.isEmpty()) {
            errorMessages = "Title is required.";
        }
        if (accountNo.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Account Number is required.";
        }
        if (description.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Description is required.";
        }
        if (mAccountType == null) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Account Type is required.";
        }
        if (!errorMessages.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    errorMessages);
            return;
        }

        Account account = new Account(title, description, accountNo, mAccountType);
        mAddAccountPresenter.addAccount(account);
    }

    /**
     * {@link AddAccountView} Implementation
     */

    @Override
    public void showAddAccountInProgress() {
        showProgressDialog(getString(R.string.global_dialog_message_please_wait),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mAddAccountPresenter.cancelAddAccount();
                    }
                });
    }

    @Override
    public void showAddAccountSuccess(Account account) {
        dismissProgressDialog();
        Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT)
                .show();
        Intent intent = HomeActivity.newIntent(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showAddAccountError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showAddAccountCancelled() {
        dismissProgressDialog();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled),
                Toast.LENGTH_SHORT).show();
    }
}
