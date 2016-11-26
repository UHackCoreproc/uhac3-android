package ph.coreproc.android.uhac3.domain.interactors.user;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by johneris on 05/11/2016.
 */

public class LoginInteractorImpl extends BaseInteractor implements LoginInteractor {

    private UserRepository mUserRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public LoginInteractorImpl(UserRepository userRepository,
                               @Named("execution") Scheduler executionThread,
                               @Named("post_execution") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mUserRepository = userRepository;
    }

    @Override
    public void login(LoginParams loginParams, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mUserRepository.login(loginParams)
                .compose(this.<User>applySchedulers())
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User appuser) {
                        mUserRepository.saveLoggedInUser(appuser);
                        return Observable.just(appuser);
                    }
                })
                .subscribe(new DefaultSubscriber<User>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onLoginError(errorBundle);
                    }

                    @Override
                    public void onNext(User user) {
                        mCallback.onLoginSuccess(user);
                    }
                });
    }

    @Override
    public void cancelLogin() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onLoginCancelled();
        }
    }
}
