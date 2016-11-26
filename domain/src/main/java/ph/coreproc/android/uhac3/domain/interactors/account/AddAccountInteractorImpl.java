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

public class AddAccountInteractorImpl extends BaseInteractor
        implements AddAccountInteractor {

    private AccountRepository mAccountRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public AddAccountInteractorImpl(@Named("execution") Scheduler executionThread,
                                    @Named("post_execution") Scheduler postExecutionThread,
                                    AccountRepository accountRepository) {
        super(executionThread, postExecutionThread);
        mAccountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mAccountRepository.addAccount(account)
                .compose(this.<Account>applySchedulers())
                .subscribe(new DefaultSubscriber<Account>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onAddAccountError(errorBundle);
                    }

                    @Override
                    public void onNext(Account a) {
                        mCallback.onAddAccountSuccess(a);
                    }
                });
    }

    @Override
    public void cancelAddAccount() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onAddAccountCancelled();
        }
    }
}
