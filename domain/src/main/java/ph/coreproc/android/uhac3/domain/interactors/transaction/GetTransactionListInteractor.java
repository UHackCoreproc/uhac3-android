package ph.coreproc.android.uhac3.domain.interactors.transaction;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 26/11/2016.
 */

public interface GetTransactionListInteractor {

    interface Callback {
        void onGetTransactionListSuccess(List<Transaction> transactionList);

        void onGetTransactionListError(ErrorBundle errorBundle);

        void onGetTransactionListCancelled();
    }

    void getTransactionList(Callback callback);

    void cancelGetTransactionList();

}
