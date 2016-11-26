package ph.coreproc.android.appname.domain.interactors.user;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/11/2016.
 */

public interface GetUserInteractor {

    interface Callback {
        void onGetUserSuccess(User user);

        void onGetUserError(ErrorBundle errorBundle);

        void onGetUserCancelled();
    }

    void getUser(Callback callback);

    void cancelGetUser();

}
