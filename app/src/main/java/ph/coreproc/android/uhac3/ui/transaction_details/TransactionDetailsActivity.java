package ph.coreproc.android.uhac3.ui.transaction_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

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
    }

}
