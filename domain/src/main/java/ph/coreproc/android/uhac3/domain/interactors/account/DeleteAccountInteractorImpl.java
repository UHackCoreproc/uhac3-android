package ph.coreproc.android.uhac3.domain.interactors.account;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.repositories.AccountRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 27/11/2016.
 */

public class DeleteAccountInteractorImpl extends BaseInteractor
        implements DeleteAccountInteractor {

    private AccountRepository mAccountRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public DeleteAccountInteractorImpl(@Named("execution") Scheduler executionThread,
                                       @Named("post_execution") Scheduler postExecutionThread,
                                       AccountRepository accountRepository) {
        super(executionThread, postExecutionThread);
        mAccountRepository = accountRepository;
    }

    @Override
    public void deleteAccount(Account account, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mAccountRepository.deleteAccount(account)
                .compose(this.<Void>applySchedulers())
                .subscribe(new DefaultSubscriber<Void>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onDeleteAccountError(errorBundle);
                    }

                    @Override
                    public void onCompleted() {
                        mCallback.onDeleteAccountSuccess();
                    }
                });
    }

    @Override
    public void cancelDeleteAccount() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onDeleteAccountCancelled();
        }
    }
}
