package ph.coreproc.android.uhac3.domain.interactors.util;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;

/**
 * Created by johneris on 26/11/2016.
 */

public interface VerifyMobileNumberInteractor {

    interface Callback {
        void onVerifyMobileNumberSuccess(MobileNumberVerification mobileNumberVerification);

        void onVerifyMobileNumberError(ErrorBundle errorBundle);

        void onVerifyMobileNumberCancelled();
    }

    void verifyMobileNumber(VerifyMobileNumberParams verifyMobileNumberParams,
                            Callback callback);

    void cancelVerifyMobileNumber();
}
