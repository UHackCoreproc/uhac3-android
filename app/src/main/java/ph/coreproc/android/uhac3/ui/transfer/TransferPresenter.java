package ph.coreproc.android.uhac3.ui.transfer;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;

/**
 * Created by johneris on 27/11/2016.
 */

public interface TransferPresenter {

    void setTrasnferView(TransferView trasnferView);

    void showTransferInProgres();

    void showTrasnferError(ErrorBundle errorBundle);

    void showTransferCancelled();
}
