package ph.coreproc.android.uhac3.ui.amount_remarks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.transfer.TransferActivity;
import ph.coreproc.android.uhac3.ui.util.NumberTextWatcher;

/**
 * Created by johneris on 27/11/2016.
 */

public class InputAmountAndRemarksActivity extends BaseActivity {

    private static String ARGS_SOURCE_ACCOUNT = "ARGS_SOURCE_ACCOUNT";
    private static String ARGS_RECIPIENT_ACCOUNT = "ARGS_RECIPIENT_ACCOUNT";
    private static String ARGS_MOBILE_NUMBER = "ARGS_MOBILE_NUMBER";

    public static Intent newIntent(Context context, Account sourceAccount,
                                   @Nullable Account recipientAccount, String mobileNumber) {
        Intent intent = new Intent(context, InputAmountAndRemarksActivity.class);

        String sourceAccountJson = getGsonForBundle().toJson(sourceAccount);
        intent.putExtra(ARGS_SOURCE_ACCOUNT, sourceAccountJson);

        if (recipientAccount != null) {
            String recipientAccountJson = getGsonForBundle().toJson(recipientAccount);
            intent.putExtra(ARGS_RECIPIENT_ACCOUNT, recipientAccountJson);
        }

        intent.putExtra(ARGS_MOBILE_NUMBER, mobileNumber);
        return intent;
    }

    @BindView(R.id.amountEditText)
    EditText mAmountEditText;

    @BindView(R.id.remarksEditText)
    EditText mRemarksEditText;

    private Account mSourceAccount;

    @Nullable
    private Account mRecipientAccount;

    private String mMobileNumber;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_input_amount_and_remarks;
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
        initUI();
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

    private void initUI() {
        initToolbar(getString(R.string.activity_amount_remarks_title), true);
        initAmountEditTextListener();
    }

    private void initAmountEditTextListener() {
        mAmountEditText.addTextChangedListener(new NumberTextWatcher(mAmountEditText));
    }

    public static double getDouble(String text) {
        String allowedCharacters = "0123456789.";
        String strDouble = "";
        for (int i = 0; i < text.length(); i++) {
            String character = text.charAt(i) + "";
            if (allowedCharacters.contains(character)) {
                strDouble += character;
            }
        }
        if (strDouble.isEmpty() || strDouble.equals(".")) {
            return 0;
        }
        return Double.parseDouble(strDouble);
    }

    @OnClick(R.id.submitButton)
    public void onSubmitClicked() {
        double amount = getDouble(mAmountEditText.getText().toString());
        String remarks = mRemarksEditText.getText().toString();

        if (amount <= 0) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    "Please enter amount.");
            return;
        }

        Intent intent = TransferActivity.newIntent(mContext, mSourceAccount,
                mRecipientAccount, mMobileNumber, amount, remarks);
        startActivity(intent);
    }

}
