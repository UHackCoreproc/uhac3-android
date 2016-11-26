package ph.coreproc.android.appname.ui.home;

import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/10/2016.
 */

public interface HomePresenter {

    void setHomeView(HomeView homeView);

    User getLoggedInUser();

}
