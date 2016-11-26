package ph.coreproc.android.uhac3.ui.transaction_list;

import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.transaction.GetTransactionListInteractor;
import ph.coreproc.android.uhac3.domain.interactors.transaction.GetTransactionListOfAccountInteractor;
import ph.coreproc.android.uhac3.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 26/11/2016.
 */

public class TransactionListPresenterImpl implements TransactionListPresenter {

    private TransactionListView mTransactionListView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;
    private GetTransactionListInteractor mGetTransactionListInteractor;
    private GetTransactionListOfAccountInteractor mGetTransactionListOfAccountInteractor;

    @Inject
    public TransactionListPresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor,
                                        GetTransactionListInteractor getTransactionListInteractor,
                                        GetTransactionListOfAccountInteractor getTransactionListOfAccountInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
        mGetTransactionListInteractor = getTransactionListInteractor;
        mGetTransactionListOfAccountInteractor = getTransactionListOfAccountInteractor;
    }

    public void setTransactionListView(TransactionListView transactionListView) {
        mTransactionListView = transactionListView;
    }

    @Override
    public void getTransactionList() {
        if (mTransactionListView == null) {
            return;
        }

        mTransactionListView.showLoadingTransactionList();
        mGetTransactionListInteractor.getTransactionList(new GetTransactionListInteractor.Callback() {
            @Override
            public void onGetTransactionListSuccess(List<Transaction> transactionList) {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListSuccess(transactionList);
                }
            }

            @Override
            public void onGetTransactionListError(ErrorBundle errorBundle) {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListError(errorBundle);
                }
            }

            @Override
            public void onGetTransactionListCancelled() {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListCancelled();
                }
            }
        });
    }

    @Override
    public void cancelGetTransactionList() {
        mGetTransactionListInteractor.cancelGetTransactionList();
    }

    @Override
    public void getTransactionListOfAccount(Account account) {
        if (mTransactionListView == null) {
            return;
        }

        mTransactionListView.showLoadingTransactionList();
        mGetTransactionListOfAccountInteractor.getTransactionListOfAccount(account, new GetTransactionListOfAccountInteractor.Callback() {
            @Override
            public void onGetTransactionListOfAccountSuccess(List<Transaction> transactionList) {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListSuccess(transactionList);
                }
            }

            @Override
            public void onGetTransactionListOfAccountError(ErrorBundle errorBundle) {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListError(errorBundle);
                }
            }

            @Override
            public void onGetTransactionListOfAccountCancelled() {
                if (mTransactionListView != null) {
                    mTransactionListView.showLoadingTransactionListCancelled();
                }
            }
        });
    }

    @Override
    public void cancelGetTransactionListOfAccount() {
        mGetTransactionListOfAccountInteractor.cancelGetTransactionListOfAccount();
    }
}
