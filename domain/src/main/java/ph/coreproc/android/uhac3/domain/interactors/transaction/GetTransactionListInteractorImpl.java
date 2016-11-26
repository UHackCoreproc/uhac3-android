package ph.coreproc.android.uhac3.domain.interactors.transaction;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.repositories.TransactionRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 26/11/2016.
 */

public class GetTransactionListInteractorImpl extends BaseInteractor
        implements GetTransactionListInteractor {

    private TransactionRepository mTransactionRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public GetTransactionListInteractorImpl(@Named("execution") Scheduler executionThread,
                                            @Named("post_execution") Scheduler postExecutionThread,
                                            TransactionRepository transactionRepository) {
        super(executionThread, postExecutionThread);
        mTransactionRepository = transactionRepository;
    }

    @Override
    public void getTransactionList(Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mTransactionRepository.getTransactionList()
                .compose(this.<List<Transaction>>applySchedulers())
                .subscribe(new DefaultSubscriber<List<Transaction>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onGetTransactionListError(errorBundle);
                    }

                    @Override
                    public void onNext(List<Transaction> transactionList) {
                        mCallback.onGetTransactionListSuccess(transactionList);
                    }
                });
    }

    @Override
    public void cancelGetTransactionList() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onGetTransactionListCancelled();
        }
    }
}
