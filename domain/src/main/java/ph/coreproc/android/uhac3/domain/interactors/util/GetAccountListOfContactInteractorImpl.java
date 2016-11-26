package ph.coreproc.android.uhac3.domain.interactors.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.repositories.UtilRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 26/11/2016.
 */

public class GetAccountListOfContactInteractorImpl extends BaseInteractor
        implements GetAccountListOfContactInteractor {

    private UtilRepository mUtilRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public GetAccountListOfContactInteractorImpl(@Named("execution") Scheduler executionThread,
                                                 @Named("post_execution") Scheduler postExecutionThread,
                                                 UtilRepository utilRepository) {
        super(executionThread, postExecutionThread);
        mUtilRepository = utilRepository;
    }

    @Override
    public void getAccountListOfContact(String mobileNumber, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mUtilRepository.getAccountListOfContact(mobileNumber)
                .compose(this.<List<Account>>applySchedulers())
                .subscribe(new DefaultSubscriber<List<Account>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onGetAccountListOfContactError(errorBundle);
                    }

                    @Override
                    public void onNext(List<Account> accountList) {
                        mCallback.onGetAccountListOfContactSuccess(accountList);
                    }
                });
    }

    @Override
    public void cancelgetAccountListOfContact() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onGetAccountListOfContactCancelled();
        }
    }
}
