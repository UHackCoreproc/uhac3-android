package ph.coreproc.android.appname.ui.home;

import javax.inject.Inject;

import ph.coreproc.android.appname.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/10/2016.
 */

public class HomePresenterImpl implements HomePresenter {

    private HomeView mHomeView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;

    @Inject
    public HomePresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
    }

    @Override
    public void setHomeView(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public User getLoggedInUser() {
        return mGetLoggedInUserInteractor.getLoggedInUser();
    }

}
