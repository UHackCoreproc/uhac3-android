package ph.coreproc.android.appname.domain.interactors.user;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.User;
import ph.coreproc.android.appname.domain.models.params.LoginParams;

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
