package ph.coreproc.android.appname.domain.interactors.user;


import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/10/2016.
 */

public interface GetLoggedInUserInteractor {

    /**
     * Get currently logged in user
     *
     * @return {@link User} if user is logged in
     * NULL if user is not logged in
     */
    User getLoggedInUser();

}
