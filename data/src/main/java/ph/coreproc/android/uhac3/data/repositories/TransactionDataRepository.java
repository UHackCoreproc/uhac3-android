package ph.coreproc.android.uhac3.data.repositories;

import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.exceptions.ApiHttpErrorBundle;
import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.data.net.models.response.ApiHttpErrorResponse;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;
import ph.coreproc.android.uhac3.domain.repositories.TransactionRepository;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

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
        long accountId = transferParams.getRecipientAccount().getId();
        return mApiService.tranfer(accountId, transferParams)
                .flatMap(new Func1<Response<Transaction>, Observable<Transaction>>() {
                    @Override
                    public Observable<Transaction> call(Response<Transaction> response) {
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
//        Transaction transaction = new Transaction();
//        transaction.setStatus("COMPLETED");
//        transaction.setReferenceNumber("1234567890");
//        transaction.setSourceMobileNo("09123456789");
//        transaction.setRecipientMobileNo("09123456789");
//        transaction.setAmount(5000);
//        return Observable.just(transaction);
    }

    @Override
    public Observable<List<Transaction>> getTransactionList() {
        return mApiService.getTransactionList()
                .flatMap(new Func1<Response<List<Transaction>>, Observable<List<Transaction>>>() {
                    @Override
                    public Observable<List<Transaction>> call(Response<List<Transaction>> response) {
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
//        List<Transaction> transactionList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Transaction transaction = new Transaction();
//            transaction.setStatus("COMPLETED");
//            transaction.setReferenceNumber("1234567890");
//            transaction.setSourceMobileNo("09123456789");
//            transaction.setRecipientMobileNo("09123456789");
//            transaction.setAmount(5000);
//            transactionList.add(transaction);
//        }
//        return Observable.just(transactionList);
    }

    @Override
    public Observable<List<Transaction>> getTransactionList(Account account) {
        return mApiService.getTransactionList(account.getId(), account)
                .flatMap(new Func1<Response<List<Transaction>>, Observable<List<Transaction>>>() {
                    @Override
                    public Observable<List<Transaction>> call(Response<List<Transaction>> response) {
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
//        List<Transaction> transactionList = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            Transaction transaction = new Transaction();
//            transaction.setStatus("COMPLETED");
//            transaction.setReferenceNumber("1234567890");
//            transaction.setSourceMobileNo("09123456789");
//            transaction.setRecipientMobileNo("09123456789");
//            transaction.setAmount(5000);
//            transactionList.add(transaction);
//        }
//        return Observable.just(transactionList);
    }

    @Override
    public Observable<Transaction> redeemCoupon(RedeemCouponParams redeemCouponParams) {
        return mApiService.redeemCoupon(redeemCouponParams)
                .flatMap(new Func1<Response<Transaction>, Observable<Transaction>>() {
                    @Override
                    public Observable<Transaction> call(Response<Transaction> response) {
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
//        Transaction transaction = new Transaction();
//        transaction.setStatus("COMPLETED");
//        transaction.setReferenceNumber("1234567890");
//        transaction.setSourceMobileNo("09123456789");
//        transaction.setRecipientMobileNo("09123456789");
//        transaction.setAmount(5000);
//        return Observable.just(transaction);
    }
}
