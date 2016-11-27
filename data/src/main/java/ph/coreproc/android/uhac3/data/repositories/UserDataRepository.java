package ph.coreproc.android.uhac3.data.repositories;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.exceptions.ApiHttpErrorBundle;
import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.data.net.models.response.ApiHttpErrorResponse;
import ph.coreproc.android.uhac3.domain.models.Authorization;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by johneris on 01/10/2016.
 */
public class UserDataRepository implements UserRepository {

    private final String PREFS_USER = "PREFS_USER";

    private ApiService mApiService;
    private ApiErrorUtil mApiErrorUtil;
    private SharedPreferences mSharedPreferences;
    private Gson mGson;

    @Inject
    public UserDataRepository(ApiService apiService, ApiErrorUtil apiErrorUtil,
                              SharedPreferences sharedPreferences, Gson gson) {
        mApiService = apiService;
        mApiErrorUtil = apiErrorUtil;
        mSharedPreferences = sharedPreferences;
        mGson = gson;
    }

    public UserDataRepository(Gson gson, SharedPreferences sharedPreferences) {
        mGson = gson;
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public User getLoggedInUser() {
        String appuserJsonString = mSharedPreferences.getString(PREFS_USER, "");
        if (appuserJsonString.isEmpty()) {
            return null;
        }

        User user = mGson.fromJson(appuserJsonString, User.class);
        return user;
    }

    @Override
    public void saveLoggedInUser(User user) {
        String userJson = mGson.toJson(user);
        mSharedPreferences.edit().putString(PREFS_USER, userJson).commit();
    }

    @Override
    public void logout() {
        mSharedPreferences.edit().putString(PREFS_USER, "").commit();
    }

    @Override
    public Observable<User> login(LoginParams loginParams) {
        return mApiService.login(loginParams)
                .flatMap(new Func1<Response<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            String apiKey = response.headers().get(ApiService.AUTHORIZATION);
                            user.setAuthorization(new Authorization(apiKey));
                            return Observable.just(user);
                        }
                        ApiHttpErrorResponse apiHttpErrorResponse = mApiErrorUtil.parseError(response);
                        ApiHttpErrorBundle apiHttpErrorBundle =
                                new ApiHttpErrorBundle(apiHttpErrorResponse.getError().getHttpCode(),
                                        apiHttpErrorResponse.getError().getMessage());
                        return Observable.error(apiHttpErrorBundle);
                    }
                });
        // get header X-Authorization
//        return Observable.just(new User());
    }

    @Override
    public Observable<User> register(RegisterParams registerParams) {
        return mApiService.register(registerParams)
                .flatMap(new Func1<Response<User>, Observable<User>>() {
                    @Override
                    public Observable<User> call(Response<User> response) {
                        if (response.isSuccessful()) {
                            User user = response.body();
                            String apiKey = response.headers().get(ApiService.AUTHORIZATION);
                            user.setAuthorization(new Authorization(apiKey));
                            return Observable.just(user);
                        }
                        ApiHttpErrorResponse apiHttpErrorResponse = mApiErrorUtil.parseError(response);
                        ApiHttpErrorBundle apiHttpErrorBundle =
                                new ApiHttpErrorBundle(apiHttpErrorResponse.getError().getHttpCode(),
                                        apiHttpErrorResponse.getError().getMessage());
                        return Observable.error(apiHttpErrorBundle);
                    }
                });
        // get header X-Authorization
//        User user = new User();
//        user.setAvatarUrl("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/14991805_1484009634949793_1187554488007618811_n.jpg?_nc_eui2=v1%3AAeGcIbbsS1iuoCHIOaoCyI41tHUPsVxRCGUeKJj3BH8eIjEuVfliLpZvGAeEhEfWoueAfIF1_MizDgGJPQJPREIabn3XY4crng8Sml96wtf-Fg&oh=05f66d8dedf3be9d4f367587c1705955&oe=58B3D43B");
//        user.setFirstName("John Eris");
//        user.setLastName("Villanueva");
//        user.setEmail("eris.villanueva@coreproc.ph");
//        user.setMobileNumberVerification(new MobileNumberVerification("09753966346", "1234"));
//        return Observable.just(user);
    }

    @Override
    public Observable<User> getUser() {
        return Observable.just(new User());
    }

    @Override
    public Observable<User> updateUser(User user) {
        return Observable.just(new User());
    }

}
