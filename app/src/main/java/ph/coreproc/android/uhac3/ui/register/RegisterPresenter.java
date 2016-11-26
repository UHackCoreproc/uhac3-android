package ph.coreproc.android.uhac3.ui.register;

import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;

/**
 * Created by johneris on 26/11/2016.
 */

public interface RegisterPresenter {

    void setRegisterView(RegisterView registerView);

    void register(RegisterParams registerParams);

    void cancelRegister();

}
