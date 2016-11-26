package ph.coreproc.android.uhac3.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.repositories.AccountRepository;
import rx.Observable;

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
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            accountList.add(new Account());
        }
        return Observable.just(accountList);
    }

}
