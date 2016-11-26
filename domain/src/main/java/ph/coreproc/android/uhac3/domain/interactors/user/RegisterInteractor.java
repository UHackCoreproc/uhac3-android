package ph.coreproc.android.uhac3.domain.interactors.user;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;

/**
 * Created by johneris on 26/11/2016.
 */

public interface RegisterInteractor {

    interface Callback {
        void onRegisterSuccess(User user);

        void onRegisterError(ErrorBundle errorBundle);

        void onRegisterCancelled();
    }

    void register(RegisterParams registerParams, Callback callback);

    void cancelRegister();

}
