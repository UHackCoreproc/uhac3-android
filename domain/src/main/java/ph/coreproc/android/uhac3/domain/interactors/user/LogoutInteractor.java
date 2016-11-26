package ph.coreproc.android.uhac3.domain.interactors.user;

import ph.coreproc.android.uhac3.domain.models.User;

/**
 * Created by johneris on 06/11/2016.
 */

public interface LogoutInteractor {

    /**
     * Logout. Clears the saved logged in {@link User}
     */
    void logout();

}
