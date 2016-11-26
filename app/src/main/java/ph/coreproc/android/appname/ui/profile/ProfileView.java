package ph.coreproc.android.appname.ui.profile;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.User;

/**
 * Created by johneris on 09/11/2016.
 */

public interface ProfileView {

    void showGetUserInProgress();

    void showGetUserSuccess(User user);

    void showGetUserError(ErrorBundle errorBundle);

    void showGetUserCancelled();

}
