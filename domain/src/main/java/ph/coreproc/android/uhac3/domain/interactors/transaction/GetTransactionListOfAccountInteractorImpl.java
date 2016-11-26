package ph.coreproc.android.uhac3.domain.interactors.transaction;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.repositories.TransactionRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 26/11/2016.
 */

public class GetTransactionListOfAccountInteractorImpl extends BaseInteractor
        implements GetTransactionListOfAccountInteractor {

    private TransactionRepository mTransactionRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public GetTransactionListOfAccountInteractorImpl(@Named("execution") Scheduler executionThread,
                                                     @Named("post_execution") Scheduler postExecutionThread,
                                                     TransactionRepository transactionRepository) {
        super(executionThread, postExecutionThread);
        mTransactionRepository = transactionRepository;
    }

    @Override
    public void getTransactionListOfAccount(Account account, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mTransactionRepository.getTransactionList(account)
                .compose(this.<List<Transaction>>applySchedulers())
                .subscribe(new DefaultSubscriber<List<Transaction>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onGetTransactionListOfAccountError(errorBundle);
                    }

                    @Override
                    public void onNext(List<Transaction> transactionList) {
                        mCallback.onGetTransactionListOfAccountSuccess(transactionList);
                    }
                });
    }

    @Override
    public void cancelGetTransactionListOfAccount() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onGetTransactionListOfAccountCancelled();
        }
    }
}
