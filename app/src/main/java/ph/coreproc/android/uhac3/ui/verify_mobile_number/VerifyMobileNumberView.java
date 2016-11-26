package ph.coreproc.android.uhac3.ui.verify_mobile_number;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;

/**
 * Created by johneris on 26/11/2016.
 */

public interface VerifyMobileNumberView {

    void showVerifyMobileNumberInProgress();

    void showVerifyMobileNumberSuccess();

    void showVerifyMobileNumberError(ErrorBundle errorBundle);

    void showVerifyMobileNumberCancelled();


    void showCheckCodeSuccess(String mobileNumber, String verificationCode);

    void showCheckCodeError(ErrorBundle errorBundle);

}
