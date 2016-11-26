package ph.coreproc.android.uhac3.domain.interactors.transaction;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;
import ph.coreproc.android.uhac3.domain.repositories.TransactionRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 27/11/2016.
 */

public class RedeemCouponInteractorImpl extends BaseInteractor
        implements RedeemCouponInteractor {

    private TransactionRepository mTransactionRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public RedeemCouponInteractorImpl(@Named("execution") Scheduler executionThread,
                                      @Named("post_execution") Scheduler postExecutionThread,
                                      TransactionRepository transactionRepository) {
        super(executionThread, postExecutionThread);
        mTransactionRepository = transactionRepository;
    }

    @Override
    public void redeemCoupon(RedeemCouponParams redeemCouponParams, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mTransactionRepository.redeemCoupon(redeemCouponParams)
                .compose(this.<Transaction>applySchedulers())
                .subscribe(new DefaultSubscriber<Transaction>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onRedeemCouponError(errorBundle);
                    }

                    @Override
                    public void onNext(Transaction transaction) {
                        mCallback.onRedeemCouponSuccess(transaction);
                    }
                });
    }

    @Override
    public void cancelRedeemCoupon() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onRedeemCouponCancelled();
        }
    }
}
