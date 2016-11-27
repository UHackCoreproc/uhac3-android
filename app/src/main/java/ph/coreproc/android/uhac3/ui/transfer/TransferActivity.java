package ph.coreproc.android.uhac3.ui.transfer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;

/**
 * Created by johneris on 27/11/2016.
 */

public class TransferActivity extends BaseActivity implements TransferView {

    private static String ARGS_SOURCE_ACCOUNT = "ARGS_SOURCE_ACCOUNT";
    private static String ARGS_RECIPIENT_ACCOUNT = "ARGS_RECIPIENT_ACCOUNT";
    private static String ARGS_MOBILE_NUMBER = "ARGS_MOBILE_NUMBER";
    private static String ARGS_AMOUNT = "ARGS_AMOUNT";
    private static String ARGS_REMARKS = "ARGS_REMARKS";

    public static Intent newIntent(Context context, Account sourceAccount,
                                   @Nullable Account recipientAccount, String mobileNumber,
                                   double amount, String remarks) {
        Intent intent = new Intent(context, TransferActivity.class);

        String sourceAccountJson = getGsonForBundle().toJson(sourceAccount);
        intent.putExtra(ARGS_SOURCE_ACCOUNT, sourceAccountJson);

        if (recipientAccount != null) {
            String recipientAccountJson = getGsonForBundle().toJson(recipientAccount);
            intent.putExtra(ARGS_RECIPIENT_ACCOUNT, recipientAccountJson);
        }

        intent.putExtra(ARGS_MOBILE_NUMBER, mobileNumber);
        intent.putExtra(ARGS_AMOUNT, amount);
        intent.putExtra(ARGS_REMARKS, remarks);
        return intent;
    }

    @Inject
    TransferPresenter mTransferPresenter;

    @BindView(R.id.cardTitleTextView)
    TextView mCardTitleTextView;

    @BindView(R.id.accountNoTextView)
    TextView mAccountNoTextView;

    @BindView(R.id.sourceAccountTypeTextView)
    TextView mSourceAccountTypeTextView;

    @BindView(R.id.amountTextView)
    TextView mAmountTextView;

    @BindView(R.id.recipientTextView)
    TextView mRecipientTextView;

    @BindView(R.id.remarksTextView)
    TextView mRemarksTextView;

    private Account mSourceAccount;

    @Nullable
    private Account mRecipientAccount;

    private String mMobileNumber;
    private double mAmount;
    private String mRemarks;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_transfer;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        String sourceAccountJson = bundle.getString(ARGS_SOURCE_ACCOUNT);
        mSourceAccount = getGsonForBundle().fromJson(sourceAccountJson, Account.class);

        if (bundle.containsKey(ARGS_RECIPIENT_ACCOUNT)) {
            String recipientAccountJson = bundle.getString(ARGS_RECIPIENT_ACCOUNT);
            mRecipientAccount = getGsonForBundle().fromJson(recipientAccountJson, Account.class);
        }

        mMobileNumber = bundle.getString(ARGS_MOBILE_NUMBER);
        mAmount = bundle.getDouble(ARGS_AMOUNT);
        mRemarks = bundle.getString(ARGS_REMARKS);

        getApplicationComponent().inject(this);
        mTransferPresenter.setTransferView(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        mTransferPresenter.setTransferView(null);
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
     * {@link TransferActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_transfer_title), true);
        mCardTitleTextView.setText(mSourceAccount.getTitle());
        mAccountNoTextView.setText(mSourceAccount.getAccountNumber());
        mSourceAccountTypeTextView.setText(mSourceAccount.getAccountType().getName());
        mAmountTextView.setText(toPesoFormat(mAmount));
        mRecipientTextView.setText("TO: " + (mRecipientAccount != null ?
                mRecipientAccount.getAccountType().getName() : "") + " (" + mMobileNumber + ")");
        mRemarksTextView.setText("Remarks: " + mRemarks);
    }

    @OnClick(R.id.submitButton)
    public void onSubmitClicked() {
        TransferParams transferParams = new TransferParams(mSourceAccount, mRecipientAccount,
                mMobileNumber, mAmount, mRemarks);
        mTransferPresenter.trasfer(transferParams);
    }

    public String toPesoFormat(double peso) {
        return "₱" + new DecimalFormat("#,###,##0.00").format(peso);
    }

    /**
     * {@link TransferView} Implementation
     */

    @Override
    public void showTransferInProgress() {
        showProgressDialog(getString(R.string.global_dialog_message_please_wait),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mTransferPresenter.cancelTransfer();
                    }
                });
    }

    @Override
    public void showTransferSuccess(Transaction transaction) {
        dismissProgressDialog();
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setTitle(getString(R.string.global_dialog_title_success))
                .setMessage("Successful Transaction!")
                .setPositiveButton(getString(R.string.global_dialog_action_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = HomeActivity.newIntent(mContext, true);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                .create();
        showAlertDialog(alertDialog);
    }

    @Override
    public void showTransferError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showTransferCancelled() {
        dismissProgressDialog();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled), Toast.LENGTH_SHORT)
                .show();
    }

}
