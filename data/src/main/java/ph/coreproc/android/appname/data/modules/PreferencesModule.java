package ph.coreproc.android.appname.data.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class PreferencesModule {

    private String mPackageName;

    public PreferencesModule(String packageName) {
        mPackageName = packageName;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(mPackageName, Context.MODE_PRIVATE);
    }

}
