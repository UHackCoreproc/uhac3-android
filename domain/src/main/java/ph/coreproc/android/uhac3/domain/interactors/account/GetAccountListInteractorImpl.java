package ph.coreproc.android.uhac3.domain.interactors.account;

import java.util.List;

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
 * Created by johneris on 26/11/2016.
 */

public class GetAccountListInteractorImpl extends BaseInteractor
        implements GetAccountListInteractor {

    private AccountRepository mAccountRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public GetAccountListInteractorImpl(@Named("execution") Scheduler executionThread,
                                        @Named("post_execution") Scheduler postExecutionThread,
                                        AccountRepository accountRepository) {
        super(executionThread, postExecutionThread);
        mAccountRepository = accountRepository;
    }

    @Override
    public void getAccountList(Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mAccountRepository.getAccountList()
                .compose(this.<List<Account>>applySchedulers())
                .subscribe(new DefaultSubscriber<List<Account>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onGetAccountListError(errorBundle);
                    }

                    @Override
                    public void onNext(List<Account> accountList) {
                        mCallback.onGetAccountListSuccess(accountList);
                    }
                });
    }

    @Override
    public void cancelGetAccountList() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onGetAccountListCancelled();
        }
    }
}
