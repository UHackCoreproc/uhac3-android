package ph.coreproc.android.uhac3.domain.interactors.user;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;

/**
 * Created by johneris on 05/11/2016.
 */

public interface LoginInteractor {

    interface Callback {
        void onLoginSuccess(User appuser);

        void onLoginError(ErrorBundle errorBundle);

        void onLoginCancelled();
    }

    void login(LoginParams loginParams, Callback callback);

    void cancelLogin();
}
