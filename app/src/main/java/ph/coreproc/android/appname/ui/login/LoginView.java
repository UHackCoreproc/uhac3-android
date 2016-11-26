package ph.coreproc.android.appname.ui.login;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;

/**
 * Created by johneris on 06/11/2016.
 */

public interface LoginView {

    void showLoginProgress();

    void showLoginSuccess();

    void showLoginError(ErrorBundle errorBundle);

    void showLoginCancelled();

}
