package ph.coreproc.android.uhac3.data.repositories;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.data.net.ApiErrorUtil;
import ph.coreproc.android.uhac3.data.net.ApiService;
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
        return Observable.just(new User());
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
