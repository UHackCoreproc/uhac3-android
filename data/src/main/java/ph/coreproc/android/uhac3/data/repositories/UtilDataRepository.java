package ph.coreproc.android.uhac3.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import ph.coreproc.android.uhac3.domain.repositories.UtilRepository;
import rx.Observable;

/**
 * Created by johneris on 26/11/2016.
 */

public class UtilDataRepository implements UtilRepository {

    private ApiService mApiService;
    private ApiErrorUtil mApiErrorUtil;

    @Inject
    public UtilDataRepository(ApiService apiService, ApiErrorUtil apiErrorUtil) {
        mApiService = apiService;
        mApiErrorUtil = apiErrorUtil;
    }

    @Override
    public Observable<MobileNumberVerification> verifyMobileNumber(VerifyMobileNumberParams verifyMobileNumberParams) {
        MobileNumberVerification mobileNumberVerification = new MobileNumberVerification();
        mobileNumberVerification.setMobileNumber("09753966346");
        mobileNumberVerification.setVerificationCode("1234");
        return Observable.just(mobileNumberVerification);
    }

    @Override
    public Observable<List<Account>> getAccountListOfContact(String mobileNumber) {
        List<Account> accountList = new ArrayList<>();
        return Observable.just(accountList);
    }

}
