package ph.coreproc.android.uhac3.data.repositories;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
import ph.coreproc.android.uhac3.domain.models.MobileNumberVerification;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Observable;

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
        // get header X-Authorization
        return Observable.just(new User());
    }

    @Override
    public Observable<User> register(RegisterParams registerParams) {
        // get header X-Authorization
        User user = new User();
        user.setAvatarUrl("https://scontent-hkg3-1.xx.fbcdn.net/v/t1.0-9/14991805_1484009634949793_1187554488007618811_n.jpg?_nc_eui2=v1%3AAeGcIbbsS1iuoCHIOaoCyI41tHUPsVxRCGUeKJj3BH8eIjEuVfliLpZvGAeEhEfWoueAfIF1_MizDgGJPQJPREIabn3XY4crng8Sml96wtf-Fg&oh=05f66d8dedf3be9d4f367587c1705955&oe=58B3D43B");
        user.setFirstName("John Eris");
        user.setLastName("Villanueva");
        user.setEmail("eris.villanueva@coreproc.ph");
        user.setMobileNumberVerification(new MobileNumberVerification("09753966346", "1234"));
        return Observable.just(user);
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
