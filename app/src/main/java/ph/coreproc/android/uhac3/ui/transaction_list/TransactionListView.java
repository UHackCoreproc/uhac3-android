package ph.coreproc.android.uhac3.ui.transaction_list;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 26/11/2016.
 */

public interface TransactionListView {

    void showLoadingTransactionList();

    void showLoadingTransactionListSuccess(List<Transaction> transactionList);

    void showLoadingTransactionListError(ErrorBundle errorBundle);

    void showLoadingTransactionListCancelled();

}
