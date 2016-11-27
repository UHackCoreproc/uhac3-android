package ph.coreproc.android.uhac3.data.repositories;

import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.exceptions.ApiHttpErrorBundle;
import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.data.net.models.response.ApiHttpErrorResponse;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import ph.coreproc.android.uhac3.domain.repositories.UtilRepository;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

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
        return mApiService.verifyMobileNumber(verifyMobileNumberParams.getMobileNumber())
                .flatMap(new Func1<Response<MobileNumberVerification>, Observable<MobileNumberVerification>>() {
                    @Override
                    public Observable<MobileNumberVerification> call(Response<MobileNumberVerification> response) {
                        if (response.isSuccessful()) {
                            return Observable.just(response.body());
                        }
                        ApiHttpErrorResponse apiHttpErrorResponse = mApiErrorUtil.parseError(response);
                        ApiHttpErrorBundle apiHttpErrorBundle =
                                new ApiHttpErrorBundle(apiHttpErrorResponse.getError().getHttpCode(),
                                        apiHttpErrorResponse.getError().getMessage());
                        return Observable.error(apiHttpErrorBundle);
                    }
                });
//        MobileNumberVerification mobileNumberVerification = new MobileNumberVerification();
//        mobileNumberVerification.setMobileNumber("09753966346");
//        mobileNumberVerification.setVerificationCode("1234");
//        return Observable.just(mobileNumberVerification);
    }

    @Override
    public Observable<List<Account>> getAccountListOfContact(String mobileNumber) {
        return mApiService.getAccountListOfContact(mobileNumber)
                .flatMap(new Func1<Response<List<Account>>, Observable<List<Account>>>() {
                    @Override
                    public Observable<List<Account>> call(Response<List<Account>> response) {
                        if (response.isSuccessful()) {
                            return Observable.just(response.body());
                        }
                        ApiHttpErrorResponse apiHttpErrorResponse = mApiErrorUtil.parseError(response);
                        ApiHttpErrorBundle apiHttpErrorBundle =
                                new ApiHttpErrorBundle(apiHttpErrorResponse.getError().getHttpCode(),
                                        apiHttpErrorResponse.getError().getMessage());
                        return Observable.error(apiHttpErrorBundle);
                    }
                });
//        List<Account> accountList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            Account account = new Account();
//            AccountType accountType = new AccountType(i, "Type " + i);
//            account.setAccountType(accountType);
//            accountList.add(account);
//        }
//        return Observable.just(accountList);
    }

}
