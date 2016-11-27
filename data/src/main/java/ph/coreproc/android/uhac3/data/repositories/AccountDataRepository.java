package ph.coreproc.android.uhac3.data.repositories;

import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.exceptions.ApiHttpErrorBundle;
import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.data.net.models.response.ApiHttpErrorResponse;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.repositories.AccountRepository;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by johneris on 26/11/2016.
 */

public class AccountDataRepository implements AccountRepository {

    private ApiService mApiService;
    private ApiErrorUtil mApiErrorUtil;

    @Inject
    public AccountDataRepository(ApiService apiService, ApiErrorUtil apiErrorUtil) {
        mApiService = apiService;
        mApiErrorUtil = apiErrorUtil;
    }

    @Override
    public Observable<List<Account>> getAccountList() {
        return mApiService.getAccountList()
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
//        for (int i = 1; i <= 3; i++) {
//            Account account = new Account();
//            account.setTitle("Hello Card");
//            account.setAccountNumber("123");
//            account.setAccountType(new AccountType(i, "Type " + i));
//            if (i == 2) {
//                account.setBalance(new Double(12000));
//            }
//            accountList.add(account);
//        }
//        return Observable.just(accountList);
    }

    @Override
    public Observable<Account> addAccount(Account account) {
        return mApiService.createAccount(account)
                .flatMap(new Func1<Response<Account>, Observable<Account>>() {
                    @Override
                    public Observable<Account> call(Response<Account> response) {
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
//        account = new Account();
//        account.setTitle("Hello Card");
//        account.setAccountNumber("123");
//        account.setAccountType(new AccountType(1, "Type 1"));
//        return Observable.just(account);
    }

    @Override
    public Observable<Void> deleteAccount(Account account) {
        return Observable.empty();
    }

}
