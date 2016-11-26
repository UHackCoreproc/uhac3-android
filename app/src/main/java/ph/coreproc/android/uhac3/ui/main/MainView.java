package ph.coreproc.android.uhac3.ui.main;

import ph.coreproc.android.uhac3.domain.models.User;

/**
 * Created by johneris on 06/11/2016.
 */

public interface MainView {

    void showUserIsLoggedIn(User user);

    void showUserNotLoggedIn();

}
