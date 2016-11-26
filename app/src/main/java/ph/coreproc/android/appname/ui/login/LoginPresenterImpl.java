package ph.coreproc.android.appname.ui.login;

import javax.inject.Inject;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.interactors.user.LoginInteractor;
import ph.coreproc.android.appname.domain.models.User;
import ph.coreproc.android.appname.domain.models.params.LoginParams;

/**
 * Created by johneris on 01/10/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;

    private LoginInteractor mLoginInteractor;

    @Inject
    public LoginPresenterImpl(LoginInteractor loginInteractor) {
        mLoginInteractor = loginInteractor;
    }

    public void setLoginView(LoginView loginView) {
        mLoginView = loginView;
    }

    @Override
    public void login(LoginParams loginParams) {
        if (mLoginView == null) {
            return;
        }

        mLoginView.showLoginProgress();
        mLoginInteractor.login(loginParams, new LoginInteractor.Callback() {
            @Override
            public void onLoginSuccess(User user) {
                if (mLoginView != null) {
                    mLoginView.showLoginSuccess();
                }
            }

            @Override
            public void onLoginError(ErrorBundle errorBundle) {
                if (mLoginView != null) {
                    mLoginView.showLoginError(errorBundle);
                }
            }

            @Override
            public void onLoginCancelled() {
                if (mLoginView != null) {
                    mLoginView.showLoginCancelled();
                }
            }
        });
    }

    @Override
    public void cancelLogin() {
        if (mLoginView != null) {
            mLoginInteractor.cancelLogin();
        }
    }

}
