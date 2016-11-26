package ph.coreproc.android.appname.ui.profile;

import javax.inject.Inject;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.appname.domain.interactors.user.GetUserInteractor;
import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/11/2016.
 */

public class ProfilePresenterImpl implements ProfilePresenter {

    private ProfileView mProfileView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;
    private GetUserInteractor mGetUserInteractor;

    @Inject
    public ProfilePresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor,
                                GetUserInteractor getUserInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
        mGetUserInteractor = getUserInteractor;
    }

    @Override
    public void setProfileView(ProfileView profileView) {
        mProfileView = profileView;
    }

    @Override
    public void getUser() {
        if (mProfileView == null) {
            return;
        }

        mProfileView.showGetUserInProgress();
        mGetUserInteractor.getUser(new GetUserInteractor.Callback() {
            @Override
            public void onGetUserSuccess(User user) {
                if (mProfileView != null) {
                    mProfileView.showGetUserSuccess(user);
                }
            }

            @Override
            public void onGetUserError(ErrorBundle errorBundle) {
                if (mProfileView != null) {
                    mProfileView.showGetUserError(errorBundle);
                }
            }

            @Override
            public void onGetUserCancelled() {
                if (mProfileView != null) {
                    mProfileView.showGetUserCancelled();
                }
            }
        });
    }

    @Override
    public void cancelGetUser() {
        mGetUserInteractor.cancelGetUser();
    }
}
