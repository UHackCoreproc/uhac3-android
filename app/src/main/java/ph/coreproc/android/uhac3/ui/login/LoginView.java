package ph.coreproc.android.uhac3.ui.login;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;

/**
 * Created by johneris on 06/11/2016.
 */

public interface LoginView {

    void showLoginProgress();

    void showLoginSuccess();

    void showLoginError(ErrorBundle errorBundle);

    void showLoginCancelled();

}
