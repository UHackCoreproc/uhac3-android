package ph.coreproc.android.uhac3.domain.interactors.util;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import ph.coreproc.android.uhac3.domain.repositories.UtilRepository;
import rx.Scheduler;
import rx.Subscription;

/**
 * Created by johneris on 26/11/2016.
 */

public class VerifyMobileNumberInteractorImpl extends BaseInteractor
        implements VerifyMobileNumberInteractor {

    private UtilRepository mUtilRepository;

    private Subscription mSubscription;
    private Callback mCallback;

    @Inject
    public VerifyMobileNumberInteractorImpl(@Named("execution") Scheduler executionThread,
                                            @Named("post_execution") Scheduler postExecutionThread,
                                            UtilRepository utilRepository) {
        super(executionThread, postExecutionThread);
        mUtilRepository = utilRepository;
    }

    @Override
    public void verifyMobileNumber(VerifyMobileNumberParams verifyMobileNumberParams, Callback callback) {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }

        mCallback = callback;
        mSubscription = mUtilRepository.verifyMobileNumber(verifyMobileNumberParams)
                .compose(this.<MobileNumberVerification>applySchedulers())
                .subscribe(new DefaultSubscriber<MobileNumberVerification>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {
                        mCallback.onVerifyMobileNumberError(errorBundle);
                    }

                    @Override
                    public void onNext(MobileNumberVerification mobileNumberVerification) {
                        mCallback.onVerifyMobileNumberSuccess(mobileNumberVerification);
                    }
                });
    }

    @Override
    public void cancelVerifyMobileNumber() {
        if (mSubscription != null && mCallback != null) {
            mSubscription.unsubscribe();
            mCallback.onVerifyMobileNumberCancelled();
        }
    }
}
