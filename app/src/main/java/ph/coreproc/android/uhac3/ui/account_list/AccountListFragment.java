package ph.coreproc.android.uhac3.ui.account_list;

import android.content.Context;
import android.content.Intent;
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
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.adapters.AccountAdapter;
import ph.coreproc.android.uhac3.ui.adapters.DividerItemDecoration;
import ph.coreproc.android.uhac3.ui.transaction_list.TransactionListActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class AccountListFragment extends Fragment implements AccountListView, AccountAdapter.Callback {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.accountRecyclerView)
    RecyclerView mAccountRecyclerView;

    @BindView(R.id.noAccountTextView)
    TextView mNoAccountTextView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @BindView(R.id.retryButton)
    Button mRetryButton;

    @Inject
    AccountListPresenter mAccountListPresenter;

    private Context mContext;
    private AccountAdapter mAccountAdapter;

    public static AccountListFragment newInstance() {
        AccountListFragment accountListFragment = new AccountListFragment();
        return accountListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_list, container, false);

        ButterKnife.bind(this, view);
        mContext = getActivity();

        getApplicationComponent().inject(this);
        mAccountListPresenter.setAccountListView(this);

        initUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAccountListPresenter.getAccountList();
    }

    @Override
    public void onDestroyView() {
        mAccountListPresenter.setAccountListView(null);
        super.onDestroyView();
    }

    private ApplicationComponent getApplicationComponent() {
        return ((App) getActivity().getApplication()).getApplicationComponent();
    }

    private void initUI() {
        initRecyclerView(mAccountRecyclerView);
        initSwipeRefreshLayout(mSwipeRefreshLayout);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        List<Account> accountList = new ArrayList<>();
        mAccountAdapter = new AccountAdapter(accountList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(mAccountAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST)
        );
    }

    private void initSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAccountListPresenter.getAccountList();
            }
        });
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void showLoadingAccountList() {
        mSwipeRefreshLayout.setRefreshing(true);
        mProgressBar.setVisibility(View.VISIBLE);
        mNoAccountTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        mAccountRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingAccountListSuccess(List<Account> accountList) {
        hideLoadingAccountList();
        if (accountList.isEmpty()) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mNoAccountTextView.setVisibility(View.VISIBLE);
            mRetryButton.setVisibility(View.INVISIBLE);
            mAccountRecyclerView.setVisibility(View.INVISIBLE);
        } else {
            mAccountAdapter = new AccountAdapter(accountList, this);
            mAccountRecyclerView.setAdapter(mAccountAdapter);
        }
    }

    @Override
    public void showLoadingAccountListError(ErrorBundle errorBundle) {
        hideLoadingAccountList();
        errorBundle = ((BaseActivity) getActivity()).handleGlobalError(errorBundle);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNoAccountTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.VISIBLE);
        mAccountRecyclerView.setVisibility(View.INVISIBLE);
        Toast.makeText(mContext, "Error: " + errorBundle.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingAccountListCancelled() {
        hideLoadingAccountList();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled), Toast.LENGTH_SHORT).show();
    }

    private void hideLoadingAccountList() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.INVISIBLE);
        mNoAccountTextView.setVisibility(View.INVISIBLE);
        mRetryButton.setVisibility(View.INVISIBLE);
        mAccountRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAccountClicked(Account account) {
        Intent intent = TransactionListActivity.newIntent(mContext, account);
        startActivity(intent);
    }
}
