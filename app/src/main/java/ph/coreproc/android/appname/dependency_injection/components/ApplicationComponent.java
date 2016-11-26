package ph.coreproc.android.appname.dependency_injection.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ph.coreproc.android.appname.dependency_injection.modules.ApplicationModule;
import ph.coreproc.android.appname.dependency_injection.modules.PresenterModule;
import ph.coreproc.android.appname.dependency_injection.modules.UtilModule;
import ph.coreproc.android.appname.domain.modules.InteractorModule;
import ph.coreproc.android.appname.ui.BaseActivity;
import ph.coreproc.android.appname.ui.home.HomeActivity;
import ph.coreproc.android.appname.ui.login.LoginActivity;
import ph.coreproc.android.appname.ui.main.MainActivity;
import ph.coreproc.android.appname.ui.profile.ProfileActivity;
import ph.coreproc.android.appname.data.modules.DatabaseModule;
import ph.coreproc.android.appname.data.modules.NetModule;
import ph.coreproc.android.appname.data.modules.PreferencesModule;
import ph.coreproc.android.appname.data.modules.RepositoryModule;

/**
 * Created by johneris on 23/09/2016.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class, PresenterModule.class, UtilModule.class, // app
        NetModule.class, DatabaseModule.class, PreferencesModule.class, RepositoryModule.class, // data
        InteractorModule.class // domain
})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(HomeActivity homeActivity);

    void inject(ProfileActivity profileActivity);

    /**
     * Exposed to sub-graphs
     */

    // ApplicationModule
    Application application();
}
