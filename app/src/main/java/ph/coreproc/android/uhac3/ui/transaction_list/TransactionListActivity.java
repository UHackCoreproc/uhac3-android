package ph.coreproc.android.uhac3.ui.transaction_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.adapters.DividerItemDecoration;
import ph.coreproc.android.uhac3.ui.adapters.TransactionAdapter;
import ph.coreproc.android.uhac3.ui.select_contact.SelectContactActivity;
import ph.coreproc.android.uhac3.ui.transaction_details.TransactionDetailsActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class TransactionListActivity extends BaseActivity implements TransactionListView, TransactionAdapter.Callback {

    private static final String ARGS_ACCOUNT = "ARGS_ACCOUNT";

    public static Intent newIntent(Context context, Account account) {
        String accountJson = getGsonForBundle().toJson(account);
        Intent intent = new Intent(context, TransactionListActivity.class);
        intent.putExtra(ARGS_ACCOUNT, accountJson);
        return intent;
    }

    @BindView(R.id.cirleImageView)
    CircleImageView mCirleImageView;

    @BindView(R.id.accountNoTextView)
    TextView mAccountNoTextView;

    @BindView(R.id.accountTypeTextView)
    TextView mAccountTypeTextView;

    @BindView(R.id.amountTextView)
    TextView mAmountTextView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.transactionRecyclerView)
    RecyclerView mTransactionRecyclerView;

    @BindView(R.id.noTransactionTextView)
    TextView mNoTransactionTextView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.retryButton)
    Button mRetryButton;

    @Inject
    TransactionListPresenter mTransactionListPresenter;

    private TransactionAdapter mTransactionAdapter;

    private Account mAccount;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_transaction_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String accountJson = bundle.getString(ARGS_ACCOUNT);
        mAccount = getGsonForBundle().fromJson(accountJson, Account.class);

        getApplicationComponent().inject(this);
        mTransactionListPresenter.setTransactionListView(this);

        initUI();
        mTransactionListPresenter.getTransactionList();
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

    @Override
    protected void onDestroy() {
        mTransactionListPresenter.setTransactionListView(null);
        super.onDestroy();
    }

    /**
     * {@link TransactionListActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_transaction_list_title), true);
        initRecyclerView(mTransactionRecyclerView);
        initSwipeRefreshLayout(mSwipeRefreshLayout);

        if (mAccount.getAccountType().getId() == 1) {
            Glide.with(mContext).load(R.drawable.ic_unionbank).into(mCirleImageView);
        } else {
            Glide.with(mContext).load(R.drawable.ic_credit_card).into(mCirleImageView);
        }
        mAccountNoTextView.setText(mAccount.getAccountNumber());
        mAccountTypeTextView.setText(mAccount.getAccountType().getName());
        if (mAccount.getBalance() != null) {
            mAmountTextView.setText(toPesoFormat(mAccount.getBalance()));
            mAmountTextView.setVisibility(View.VISIBLE);
        }
    }

    private String toPesoFormat(double peso) {
        return "₱" + new DecimalFormat("#,###,##0.00").format(peso);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        List<Transaction> transactionList = new ArrayList<>();
        mTransactionAdapter = new TransactionAdapter(transactionList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(mTransactionAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST)
        );
    }

    private void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTransactionListPresenter.getTransactionList();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        Intent intent = SelectContactActivity.newIntent(mContext, mAccount);
        startActivity(intent);
    }

    /**
     * {@link TransactionListView} Implementation
     */

    @Override
    public void showLoadingTransactionList() {
        mSwipeRefreshLayout.setRefreshing(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mNoTransactionTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        mTransactionRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingTransactionListSuccess(List<Transaction> transactionList) {
        hideLoadingAccountList();
        if (transactionList.isEmpty()) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mNoTransactionTextView.setVisibility(View.VISIBLE);
            mRetryButton.setVisibility(View.INVISIBLE);
            mTransactionRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mTransactionAdapter = new TransactionAdapter(transactionList, this);
            mTransactionRecyclerView.setAdapter(mTransactionAdapter);
        }
    }

    @Override
    public void showLoadingTransactionListError(ErrorBundle errorBundle) {
        hideLoadingAccountList();
        errorBundle = handleGlobalError(errorBundle);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNoTransactionTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mTransactionRecyclerView.setVisibility(View.INVISIBLE);
        Toast.makeText(mContext, "Error: " + errorBundle.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingTransactionListCancelled() {
        hideLoadingAccountList();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled), Toast.LENGTH_SHORT).show();
    }

    private void hideLoadingAccountList() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNoTransactionTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        mTransactionRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTransactionClicked(Transaction transaction) {
        Intent intent = TransactionDetailsActivity.newIntent(mContext, transaction);
        startActivity(intent);
    }
}
