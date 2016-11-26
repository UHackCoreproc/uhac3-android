package ph.coreproc.android.appname.domain.interactors.user;

import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 06/11/2016.
 */

public interface LogoutInteractor {

    /**
     * Logout. Clears the saved logged in {@link User}
     */
    void logout();

}
