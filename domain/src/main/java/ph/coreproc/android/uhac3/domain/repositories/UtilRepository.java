package ph.coreproc.android.uhac3.domain.repositories;

import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
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

}
