package ph.coreproc.android.uhac3.ui.transfer;

import ph.coreproc.android.uhac3.domain.models.params.TransferParams;

/**
 * Created by johneris on 27/11/2016.
 */

public interface TransferPresenter {

    void setTransferView(TransferView transferView);

    void trasfer(TransferParams transferParams);

    void cancelTransfer();
}
