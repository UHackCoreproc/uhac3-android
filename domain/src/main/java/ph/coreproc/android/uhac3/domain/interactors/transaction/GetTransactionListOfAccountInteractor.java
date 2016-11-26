package ph.coreproc.android.uhac3.domain.interactors.transaction;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 26/11/2016.
 */

public interface GetTransactionListOfAccountInteractor {

    interface Callback {
        void onGetTransactionListOfAccountSuccess(List<Transaction> transactionList);

        void onGetTransactionListOfAccountError(ErrorBundle errorBundle);

        void onGetTransactionListOfAccountCancelled();
    }

    void getTransactionListOfAccount(Account account, Callback callback);

    void cancelGetTransactionListOfAccount();

}
