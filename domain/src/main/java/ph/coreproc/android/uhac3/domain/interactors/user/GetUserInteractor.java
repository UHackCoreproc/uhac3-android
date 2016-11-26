package ph.coreproc.android.uhac3.domain.interactors.user;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.User;

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
