package ph.coreproc.android.uhac3.ui.transaction_list;

import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public interface TransactionListPresenter {

    void setTransactionListView(TransactionListView transactionListView);

    void getTransactionList();

    void cancelGetTransactionList();

    void getTransactionListOfAccount(Account account);

    void cancelGetTransactionListOfAccount();

}
