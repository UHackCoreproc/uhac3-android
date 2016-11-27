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
import ph.coreproc.android.uhac3.domain.models.params.VerifyMobileNumberParams;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by johneris on 23/09/2016.
 */
public interface ApiService {

    String AUTHORIZATION = "X-Authorization";

    @POST("api/")
    Observable<Response<User>> register(
            @Body RegisterParams registerParams
    );

    @POST("api/")
    Observable<Response<User>> login(
            @Body LoginParams loginParams
    );

    @GET("api/")
    Observable<Response<List<Account>>> getAccountList();

    @FormUrlEncoded
    @POST("api/")
    Observable<Response<List<Account>>> getAccountListOfContact(
            @Field("mobile_number") String mobileNumber
    );

    @GET("api/")
    Observable<Response<Account>> createAccount(
            @Body Account account
    );

    @POST("api/")
    Observable<Response<Transaction>> tranfer(
            @Body TransferParams transferParams
    );

    @POST("api/")
    Observable<Response<Transaction>> redeemCoupon(
            @Body RedeemCouponParams redeemCouponParams
    );

    @GET("api/")
    Observable<Response<List<Transaction>>> getTransactionList();

    @GET("api/")
    Observable<Response<List<Transaction>>> getTransactionList(
            @Body Account account
    );

    @POST("api/")
    Observable<Response<MobileNumberVerification>> verifyMobileNumber(
            @Body VerifyMobileNumberParams verifyMobileNumberParams
    );

}
