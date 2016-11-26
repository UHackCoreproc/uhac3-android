package ph.coreproc.android.uhac3.ui.home;

import ph.coreproc.android.uhac3.domain.models.User;

/**
 * Created by johneris on 09/10/2016.
 */

public interface HomePresenter {

    void setHomeView(HomeView homeView);

    User getLoggedInUser();

}
