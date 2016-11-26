package ph.coreproc.android.uhac3.ui.verify_mobile_number;

/**
 * Created by johneris on 26/11/2016.
 */

public interface VerifyMobileNumberPresenter {

    void setVerifyMobileNumberView(VerifyMobileNumberView verifyMobileNumberView);

    void getVerificationCode(String mobileNumber);

    void cancelGetVerificationCode();

    void checkCode(String code);

}
