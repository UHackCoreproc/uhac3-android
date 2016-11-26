package ph.coreproc.android.uhac3.domain.repositories;

import java.util.List;

import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import rx.Observable;

/**
 * Created by johneris on 26/11/2016.
 */

public interface UtilRepository {

    /**
     * Verify mobile number.
     *
     * @param verifyMobileNumberParams
     * @return
     */
    Observable<MobileNumberVerification> verifyMobileNumber(VerifyMobileNumberParams verifyMobileNumberParams);

    /**
     * Get list of {@link Account} of {@link User} with mobile number {@code mobileNumber}.
     *
     * @param mobileNumber
     * @return
     */
    Observable<List<Account>> getAccountListOfContact(String mobileNumber);
}
