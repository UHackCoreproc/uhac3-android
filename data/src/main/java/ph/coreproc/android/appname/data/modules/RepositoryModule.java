package ph.coreproc.android.appname.data.modules;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ph.coreproc.android.appname.domain.repositories.UserRepository;
import ph.coreproc.android.appname.data.repositories.UserDataRepository;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class RepositoryModule {

    @Provides
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    @Named("GetLoggedInUser")
    UserRepository provideGetLoggedInUserRepository(Gson gson, SharedPreferences sharedPreferences) {
        return new UserDataRepository(gson, sharedPreferences);
    }

}
