package ph.coreproc.android.uhac3.dependency_injection.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ph.coreproc.android.uhac3.data.modules.DatabaseModule;
import ph.coreproc.android.uhac3.data.modules.NetModule;
import ph.coreproc.android.uhac3.data.modules.PreferencesModule;
import ph.coreproc.android.uhac3.data.modules.RepositoryModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.ApplicationModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.PresenterModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.UtilModule;
import ph.coreproc.android.uhac3.domain.modules.InteractorModule;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;
import ph.coreproc.android.uhac3.ui.login.LoginActivity;
import ph.coreproc.android.uhac3.ui.main.MainActivity;
import ph.coreproc.android.uhac3.ui.profile.ProfileActivity;
import ph.coreproc.android.uhac3.ui.register.RegisterActivity;
import ph.coreproc.android.uhac3.ui.verify_mobile_number.VerifyMobileNumberActivity;

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

    void inject(VerifyMobileNumberActivity verifyMobileNumberActivity);

    void inject(RegisterActivity registerActivity);

    void inject(HomeActivity homeActivity);

    void inject(ProfileActivity profileActivity);

    /**
     * Exposed to sub-graphs
     */

    // ApplicationModule
    Application application();
}
