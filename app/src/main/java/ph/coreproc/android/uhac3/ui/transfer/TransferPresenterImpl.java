package ph.coreproc.android.uhac3.ui.transfer;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.transaction.TransferInteractor;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;

/**
 * Created by johneris on 27/11/2016.
 */

public class TransferPresenterImpl implements TransferPresenter {

    private TransferView mTransferView;

    private TransferInteractor mTransferInteractor;

    @Inject
    public TransferPresenterImpl(TransferInteractor transferInteractor) {
        mTransferInteractor = transferInteractor;
    }

    @Override
    public void setTransferView(TransferView transferView) {
        mTransferView = transferView;
    }

    @Override
    public void trasfer(TransferParams transferParams) {
        if (mTransferView == null) {
            return;
        }

        mTransferView.showTransferInProgress();
        mTransferInteractor.transfer(transferParams, new TransferInteractor.Callback() {
            @Override
            public void onTrasnferSuccess(Transaction transaction) {
                if (mTransferView != null) {
                    mTransferView.showTransferSuccess(transaction);
                }
            }

            @Override
            public void onTransferError(ErrorBundle errorBundle) {
                if (mTransferView != null) {
                    mTransferView.showTransferError(errorBundle);
                }
            }

            @Override
            public void onTrasnferCancelled() {
                if (mTransferView != null) {
                    mTransferView.showTransferCancelled();
                }
            }
        });
    }

    @Override
    public void cancelTransfer() {
        mTransferInteractor.cancelTransfer();
    }
}
