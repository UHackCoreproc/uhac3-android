package ph.coreproc.android.uhac3.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.AccountType;
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
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setTitle("Hello Card");
            account.setAccountNumber("123");
            account.setAccountType(new AccountType(1, "Type 1"));
            accountList.add(account);
        }
        return Observable.just(accountList);
    }

    @Override
    public Observable<Account> addAccount(Account account) {
        account = new Account();
        account.setTitle("Hello Card");
        account.setAccountNumber("123");
        account.setAccountType(new AccountType(1, "Type 1"));
        return Observable.just(account);
    }

    @Override
    public Observable<Void> deleteAccount(Account account) {
        return Observable.empty();
    }

}
