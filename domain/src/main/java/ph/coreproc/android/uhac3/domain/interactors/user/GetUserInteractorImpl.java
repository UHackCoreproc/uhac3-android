package ph.coreproc.android.uhac3.domain.interactors.user;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 09/11/2016.
 */

public class GetUserInteractorImpl extends BaseInteractor
        implements GetUserInteractor {

    private UserRepository mUserRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public GetUserInteractorImpl(UserRepository userRepository,
                                 @Named("execution") Scheduler executionThread,
                                 @Named("post_execution") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mUserRepository = userRepository;
    }

    @Override
    public void getUser(Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mUserRepository.getUser()
                .compose(this.<User>applySchedulers())
                .subscribe(new DefaultSubscriber<User>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onGetUserError(errorBundle);
                    }

                    @Override
                    public void onNext(User user) {
                        mCallback.onGetUserSuccess(user);
                    }
                });
    }

    @Override
    public void cancelGetUser() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onGetUserCancelled();
        }
    }
}
