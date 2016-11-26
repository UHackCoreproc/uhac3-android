package ph.coreproc.android.uhac3.dependency_injection.modules;

import dagger.Module;
import dagger.Provides;
import ph.coreproc.android.uhac3.ui.home.HomePresenter;
import ph.coreproc.android.uhac3.ui.home.HomePresenterImpl;
import ph.coreproc.android.uhac3.ui.login.LoginPresenter;
import ph.coreproc.android.uhac3.ui.login.LoginPresenterImpl;
import ph.coreproc.android.uhac3.ui.main.MainPresenter;
import ph.coreproc.android.uhac3.ui.main.MainPresenterImpl;
import ph.coreproc.android.uhac3.ui.profile.ProfilePresenter;
import ph.coreproc.android.uhac3.ui.profile.ProfilePresenterImpl;
import ph.coreproc.android.uhac3.ui.register.RegisterPresenter;
import ph.coreproc.android.uhac3.ui.register.RegisterPresenterImpl;
import ph.coreproc.android.uhac3.ui.verify_mobile_number.VerifyMobileNumberPresenter;
import ph.coreproc.android.uhac3.ui.verify_mobile_number.VerifyMobileNumberPresenterImpl;

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

    @Provides
    RegisterPresenter provideRegisterPresenter(RegisterPresenterImpl registerPresenter) {
        return registerPresenter;
    }

    @Provides
    VerifyMobileNumberPresenter provideVerifyMobileNumberPresenter(VerifyMobileNumberPresenterImpl verifyMobileNumberPresenter) {
        return verifyMobileNumberPresenter;
    }

}
