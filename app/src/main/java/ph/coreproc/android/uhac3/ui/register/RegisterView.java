package ph.coreproc.android.uhac3.ui.register;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;

/**
 * Created by johneris on 26/11/2016.
 */

public interface RegisterView {

    void showRegisterInProgress();

    void showRegisterSuccess();

    void showRegisterError(ErrorBundle errorBundle);

    void showRegisterCancelled();

}
