package ph.coreproc.android.uhac3.ui.transaction_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.coreproc.android.uhac3.App;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.dependency_injection.components.ApplicationComponent;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.adapters.DividerItemDecoration;
import ph.coreproc.android.uhac3.ui.adapters.TransactionAdapter;

/**
 * Created by johneris on 26/11/2016.
 */

public class TransactionListFragment extends Fragment implements TransactionListView, TransactionAdapter.Callback {

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

    private Context mContext;
    private TransactionAdapter mTransactionAdapter;

    public static TransactionListFragment newInstance() {
        TransactionListFragment transactionListFragment = new TransactionListFragment();
        return transactionListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction_list, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        getApplicationComponent().inject(this);
        mTransactionListPresenter.setTransactionListView(this);

        initUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTransactionListPresenter.getTransactionList();
    }

    @Override
    public void onDestroyView() {
        mTransactionListPresenter.setTransactionListView(null);
        super.onDestroyView();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((App) getActivity().getApplication()).getApplicationComponent();
    }

    private void initUI() {
        initRecyclerView(mTransactionRecyclerView);
        initSwipeRefreshLayout(mSwipeRefreshLayout);
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
        errorBundle = ((BaseActivity) getActivity()).handleGlobalError(errorBundle);
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
        Toast.makeText(mContext, "Transaction Selected", Toast.LENGTH_SHORT).show();
    }
}
