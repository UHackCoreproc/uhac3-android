package ph.coreproc.android.uhac3.domain.interactors.transaction;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;

/**
 * Created by johneris on 27/11/2016.
 */

public interface TransferInteractor {

    interface Callback {
        void onTrasnferSuccess(Transaction transaction);

        void onTransferError(ErrorBundle errorBundle);

        void onTrasnferCancelled();
    }

    void transfer(TransferParams transferParams, Callback callback);

    void cancelTransfer();
}
