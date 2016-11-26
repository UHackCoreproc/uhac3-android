package ph.coreproc.android.uhac3.domain.repositories;

import java.util.List;

import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;
import rx.Observable;

/**
 * Created by johneris on 26/11/2016.
 */

public interface TransactionRepository {

    /**
     * Transfer.
     *
     * @param transferParams
     * @return
     */
    Observable<Transaction> tranfer(TransferParams transferParams);

    /**
     * Get list of all {@link Transaction}.
     *
     * @return
     */
    Observable<List<Transaction>> getTransactionList();

    /**
     * Get list of {@link Transaction} under {@link Account}.
     *
     * @param account
     * @return
     */
    Observable<List<Transaction>> getTransactionList(Account account);

}
