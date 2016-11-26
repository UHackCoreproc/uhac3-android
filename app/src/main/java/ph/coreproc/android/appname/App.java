package ph.coreproc.android.appname;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import ph.coreproc.android.appname.dependency_injection.components.ApplicationComponent;
import ph.coreproc.android.appname.dependency_injection.components.DaggerApplicationComponent;
import ph.coreproc.android.appname.dependency_injection.modules.ApplicationModule;
import ph.coreproc.android.appname.dependency_injection.modules.PresenterModule;
import ph.coreproc.android.appname.dependency_injection.modules.UtilModule;
import ph.coreproc.android.appname.domain.modules.InteractorModule;
import ph.coreproc.android.appname.data.modules.DatabaseModule;
import ph.coreproc.android.appname.data.modules.NetModule;
import ph.coreproc.android.appname.data.modules.PreferencesModule;
import ph.coreproc.android.appname.data.modules.RepositoryModule;
import rx.Scheduler;

/**
 * Created by johneris on 23/09/2016.
 */
public class App extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        // Initialize dependency injection
        initApplicationComponent(Config.BASE_URL_LIVE, Config.DATABASE_NAME,
                Config.PREFERENCES_PACKAGE, Config.EXECUTION_THREAD, Config.POST_EXECUTION_THREAD);

        // To Future Me,
        // initialization of CustomActivityOnCrash must always be before Sentry initialization
        // Sentry will not work if CustomActivityOnCrash is initialized after Sentry
        CustomActivityOnCrash.install(this);

        // Initialize Error Reporting
        if (!BuildConfig.DEBUG) {

        }
    }

    public void initApplicationComponent(String baseUrl, String databaseName,
                                         String preferencesPackageName,
                                         Scheduler executionThread, Scheduler postExecutionThread) {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule(baseUrl))
                .databaseModule(new DatabaseModule(databaseName))
                .preferencesModule(new PreferencesModule(preferencesPackageName))
                .utilModule(new UtilModule())
                .repositoryModule(new RepositoryModule())
                .interactorModule(new InteractorModule(executionThread, postExecutionThread))
                .presenterModule(new PresenterModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
