package ph.coreproc.android.uhac3.ui.verify_mobile_number;

import android.support.annotation.Nullable;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.util.VerifyMobileNumberInteractor;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import ph.coreproc.android.uhac3.errors.ErrorMessageBundle;

/**
 * Created by johneris on 26/11/2016.
 */

public class VerifyMobileNumberPresenterImpl implements VerifyMobileNumberPresenter {

    private VerifyMobileNumberView mVerifyMobileNumberView;

    private VerifyMobileNumberInteractor mVerifyMobileNumberInteractor;

    @Nullable
    private String mMobileNumber;

    @Nullable
    private String mVerificationCode;

    @Inject
    public VerifyMobileNumberPresenterImpl(VerifyMobileNumberInteractor verifyMobileNumberInteractor) {
        mVerifyMobileNumberInteractor = verifyMobileNumberInteractor;
    }

    @Override
    public void setVerifyMobileNumberView(VerifyMobileNumberView verifyMobileNumberView) {
        mVerifyMobileNumberView = verifyMobileNumberView;
    }

    @Override
    public void getVerificationCode(String mobileNumber) {
        if (mVerifyMobileNumberView == null) {
            return;
        }

        VerifyMobileNumberParams verifyMobileNumberParams = new VerifyMobileNumberParams(mobileNumber);

        mVerifyMobileNumberView.showVerifyMobileNumberInProgress();
        mVerifyMobileNumberInteractor.verifyMobileNumber(verifyMobileNumberParams,
                new VerifyMobileNumberInteractor.Callback() {
                    @Override
                    public void onVerifyMobileNumberSuccess(MobileNumberVerification mobileNumberVerification) {
                        mMobileNumber = mobileNumberVerification.getMobileNumber();
                        mVerificationCode = mobileNumberVerification.getVerificationCode();
                        if (mVerifyMobileNumberView != null) {
                            mVerifyMobileNumberView.showVerifyMobileNumberSuccess();
                        }
                    }

                    @Override
                    public void onVerifyMobileNumberError(ErrorBundle errorBundle) {
                        if (mVerifyMobileNumberView != null) {
                            mVerifyMobileNumberView.showVerifyMobileNumberError(errorBundle);
                        }
                    }

                    @Override
                    public void onVerifyMobileNumberCancelled() {
                        if (mVerifyMobileNumberView != null) {
                            mVerifyMobileNumberView.showVerifyMobileNumberCancelled();
                        }
                    }
                });
    }

    @Override
    public void cancelGetVerificationCode() {
        mVerifyMobileNumberInteractor.cancelVerifyMobileNumber();
    }

    @Override
    public void checkCode(String code) {
        if (mVerifyMobileNumberView == null) {
            return;
        }

        if (mVerificationCode == null) {
            ErrorMessageBundle errorMessageBundle = new ErrorMessageBundle(
                    "Please click resend verification code to send " +
                            "an SMS that will verify your mobile number."
            );
            if (mVerifyMobileNumberView != null) {
                mVerifyMobileNumberView.showCheckCodeError(errorMessageBundle);
            }
            return;
        }

        if (!mVerificationCode.equals(code)) {
            ErrorMessageBundle errorMessageBundle = new ErrorMessageBundle(
                    "Invalid verification code."
            );
            if (mVerifyMobileNumberView != null) {
                mVerifyMobileNumberView.showCheckCodeError(errorMessageBundle);
            }
            return;
        }

        if (mVerifyMobileNumberView != null) {
            mVerifyMobileNumberView.showCheckCodeSuccess(mMobileNumber, mVerificationCode);
        }
    }

}
