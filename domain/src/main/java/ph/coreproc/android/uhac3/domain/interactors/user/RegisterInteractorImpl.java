package ph.coreproc.android.uhac3.domain.interactors.user;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterInteractorImpl extends BaseInteractor
        implements RegisterInteractor {

    private UserRepository mUserRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public RegisterInteractorImpl(@Named("execution") Scheduler executionThread,
                                  @Named("post_execution") Scheduler postExecutionThread,
                                  UserRepository userRepository) {
        super(executionThread, postExecutionThread);
        mUserRepository = userRepository;
    }

    @Override
    public void register(RegisterParams registerParams, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mUserRepository.register(registerParams)
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
                        mCallback.onRegisterError(errorBundle);
                    }

                    @Override
                    public void onNext(User user) {
                        mCallback.onRegisterSuccess(user);
                    }
                });
    }

    @Override
    public void cancelRegister() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onRegisterCancelled();
        }
    }

}
