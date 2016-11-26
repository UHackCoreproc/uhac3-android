package ph.coreproc.android.uhac3.ui.main;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.uhac3.domain.models.User;

/**
 * Created by johneris on 06/11/2016.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;

    @Inject
    public MainPresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
    }

    @Override
    public void setMainView(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void checkLoggedInUser() {
        if (mMainView == null) {
            return;
        }

        User user = mGetLoggedInUserInteractor.getLoggedInUser();
        if (user == null) {
            mMainView.showUserNotLoggedIn();
            return;
        }
        mMainView.showUserIsLoggedIn(user);
    }

}
