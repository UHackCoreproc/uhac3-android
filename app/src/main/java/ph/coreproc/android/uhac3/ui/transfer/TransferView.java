package ph.coreproc.android.uhac3.ui.transfer;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 27/11/2016.
 */

public interface TransferView {

    void showTransferInProgress();

    void showTransferSuccess(Transaction transaction);

    void showTransferError(ErrorBundle errorBundle);

    void showTransferCancelled();

}
