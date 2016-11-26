package ph.coreproc.android.appname.ui.main;

import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 06/11/2016.
 */

public interface MainView {

    void showUserIsLoggedIn(User user);

    void showUserNotLoggedIn();

}
