package ph.coreproc.android.appname.domain.interactors.user;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/11/2016.
 */

public interface UpdateUserInteractor {

    interface Callback {
        void onUpdateUserSuccess(User user);

        void onUpdateUserError(ErrorBundle errorBundle);

        void onUpdateUserCancelled();
    }

    void updateUser(User user, Callback callback);

    void cancelUpdateUser();

}
