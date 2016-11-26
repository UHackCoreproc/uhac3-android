package ph.coreproc.android.appname.dependency_injection.modules;

import dagger.Module;
import dagger.Provides;
import ph.coreproc.android.appname.ui.home.HomePresenter;
import ph.coreproc.android.appname.ui.home.HomePresenterImpl;
import ph.coreproc.android.appname.ui.login.LoginPresenter;
import ph.coreproc.android.appname.ui.login.LoginPresenterImpl;
import ph.coreproc.android.appname.ui.main.MainPresenter;
import ph.coreproc.android.appname.ui.main.MainPresenterImpl;
import ph.coreproc.android.appname.ui.profile.ProfilePresenter;
import ph.coreproc.android.appname.ui.profile.ProfilePresenterImpl;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class PresenterModule {

    @Provides
    MainPresenter provideMainPresenter(MainPresenterImpl mainPresenter) {
        return mainPresenter;
    }

    @Provides
    LoginPresenter provideLoginPresenter(LoginPresenterImpl loginPresenter) {
        return loginPresenter;
    }

    @Provides
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    ProfilePresenter provideProfilePresenter(ProfilePresenterImpl profilePresenter) {
        return profilePresenter;
    }

}
