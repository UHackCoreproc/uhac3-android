package ph.coreproc.android.uhac3.data.net;

import java.util.List;

import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import ph.coreproc.android.uhac3.domain.models.params.TransferParams;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by johneris on 23/09/2016.
 */
public interface ApiService {

    String AUTHORIZATION = "X-Authorization";

    @POST("api/v1/auth/login")
    Observable<Response<User>> register(
            @Body RegisterParams registerParams
    );

    @POST("api/v1/auth/login")
    Observable<Response<User>> login(
            @Body LoginParams loginParams
    );

    @GET("api/v1/accounts")
    Observable<Response<List<Account>>> getAccountList();

    @FormUrlEncoded
    @POST("api/v1/accounts/get-by-mobile-number")
    Observable<Response<List<Account>>> getAccountListOfContact(
            @Field("mobile_number") String mobileNumber
    );

    @POST("api/v1/accounts")
    Observable<Response<Account>> createAccount(
            @Body Account account
    );

    @POST("api/v1/accounts/{account_id}/transactions/make")
    Observable<Response<Transaction>> tranfer(
            @Path("account_id") long accountId,
            @Body TransferParams transferParams
    );

    @POST("api/v1/")
    Observable<Response<Transaction>> redeemCoupon(
            @Body RedeemCouponParams redeemCouponParams
    );

    @GET("api/v1/transactions")
    Observable<Response<List<Transaction>>> getTransactionList();

    @GET("api/v1/accounts/{account_id}/transactions")
    Observable<Response<List<Transaction>>> getTransactionList(
            @Path("account_id") long accountId,
            @Body Account account
    );

    @FormUrlEncoded
    @POST("api/v1/mobile-number/verify")
    Observable<Response<MobileNumberVerification>> verifyMobileNumber(
            @Field("mobile_number") String mobileNumber
    );

}
