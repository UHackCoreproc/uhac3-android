package ph.coreproc.android.uhac3.ui.login;

import ph.coreproc.android.uhac3.domain.models.params.LoginParams;

/**
 * Created by johneris on 01/10/2016.
 */
public interface LoginPresenter {

    void setLoginView(LoginView loginView);

    void login(LoginParams loginParams);

    void cancelLogin();

}
