package ph.coreproc.android.uhac3.domain.repositories;

import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.LoginParams;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import rx.Observable;

/**
 * Created by johneris on 01/10/2016.
 */
public interface UserRepository {

    /**
     * Get the currently logged in {@link User}.
     *
     * @return {@link User} if user is logged in
     * NULL if user is not logged in
     */
    User getLoggedInUser();

    /**
     * Save {@link User} as logged in user.
     */
    void saveLoggedInUser(User user);

    /**
     * Logout. Clears the saved logged in {@link User}
     */
    void logout();

    /**
     * Login.
     *
     * @param loginParams the details of the user that wants to login
     * @return {@link Observable<User>} on success
     * {@link Observable#error(Throwable)} on error
     */
    Observable<User> login(LoginParams loginParams);

    /**
     * Register.
     *
     * @param registerParams the details of the user that wants tor register.
     * @return {@link Observable<User>} on success
     * {@link Observable#error(Throwable)} on error
     */
    Observable<User> register(RegisterParams registerParams);

    /**
     * Get {@link User} details.
     *
     * @return
     */
    Observable<User> getUser();

    /**
     * Update {@link User}
     *
     * @param user
     * @return
     */
    Observable<User> updateUser(User user);

}
