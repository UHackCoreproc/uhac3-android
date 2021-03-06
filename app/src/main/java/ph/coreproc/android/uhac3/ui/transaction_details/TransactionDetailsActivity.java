package ph.coreproc.android.uhac3.ui.transaction_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.BindView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.ui.BaseActivity;

/**
 * Created by johneris on 27/11/2016.
 */

public class TransactionDetailsActivity extends BaseActivity {

    private static final String ARGS_TRANSACTION = "ARGS_TRANSACTION";

    public static Intent newIntent(Context context, Transaction transaction) {
        String transactionJson = getGsonForBundle().toJson(transaction);
        Intent intent = new Intent(context, TransactionDetailsActivity.class);
        intent.putExtra(ARGS_TRANSACTION, transactionJson);
        return intent;
    }

    @BindView(R.id.referenceNoTextView)
    TextView mReferenceNoTextView;

    @BindView(R.id.statusTextView)
    TextView mStatusTextView;

    @BindView(R.id.sourceTextView)
    TextView mSourceTextView;

    @BindView(R.id.recipientTextView)
    TextView mRecipientTextView;

    @BindView(R.id.amountTextView)
    TextView mAmountTextView;

    @BindView(R.id.remarksTextView)
    TextView mRemarksTextView;

    private Transaction mTransaction;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_transaction_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String transactionJson = bundle.getString(ARGS_TRANSACTION);
        mTransaction = getGsonForBundle().fromJson(transactionJson, Transaction.class);

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
        initToolbar(getString(R.string.activity_transaction_details_title), true);

        mReferenceNoTextView.setText(mTransaction.getReferenceNumber());
        mStatusTextView.setText(mTransaction.getStatus());
        mSourceTextView.setText(mTransaction.getSourceMobileNo());
        mRecipientTextView.setText(mTransaction.getRecipientMobileNo());
        mAmountTextView.setText(toPesoFormat(mTransaction.getAmount()));

        String remarks = mTransaction.getRemarks();
        mRemarksTextView.setText(remarks != null && !remarks.isEmpty() ? remarks : "N/A");
    }

    public String toPesoFormat(double peso) {
        return "₱" + new DecimalFormat("#,###,##0.00").format(peso);
    }

}
