package ph.coreproc.android.uhac3.data.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;
import ph.coreproc.android.uhac3.domain.repositories.TransactionRepository;
import rx.Observable;

/**
 * Created by johneris on 26/11/2016.
 */

public class TransactionDataRepository implements TransactionRepository {

    private ApiService mApiService;
    private ApiErrorUtil mApiErrorUtil;

    @Inject
    public TransactionDataRepository(ApiService apiService, ApiErrorUtil apiErrorUtil) {
        mApiService = apiService;
        mApiErrorUtil = apiErrorUtil;
    }

    @Override
    public Observable<Transaction> tranfer(TransferParams transferParams) {
        return Observable.just(new Transaction());
    }

    @Override
    public Observable<List<Transaction>> getTransactionList() {
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            transactionList.add(new Transaction());
        }
        return Observable.just(transactionList);
    }

    @Override
    public Observable<List<Transaction>> getTransactionList(Account account) {
        List<Transaction> transactionList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            transactionList.add(new Transaction());
        }
        return Observable.just(transactionList);
    }

    @Override
    public Observable<Transaction> redeemCoupon(RedeemCouponParams redeemCouponParams) {
        return Observable.just(new Transaction());
    }
}
