package ph.coreproc.android.uhac3.data.modules;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ph.coreproc.android.uhac3.data.repositories.UserDataRepository;
import ph.coreproc.android.uhac3.data.repositories.UtilDataRepository;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import ph.coreproc.android.uhac3.domain.repositories.UtilRepository;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

    @Provides
    @Singleton
    @Named("GetLoggedInUser")
    UserRepository provideGetLoggedInUserRepository(Gson gson, SharedPreferences sharedPreferences) {
        return new UserDataRepository(gson, sharedPreferences);
    }

    @Provides
    @Singleton
    UtilRepository provideUtilRepository(UtilDataRepository utilDataRepository) {
        return utilDataRepository;
    }

}
