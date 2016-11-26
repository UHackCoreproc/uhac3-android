package ph.coreproc.android.uhac3.ui.register;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.uhac3.domain.interactors.user.RegisterInteractor;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView mRegisterView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;
    private RegisterInteractor mRegisterInteractor;

    @Inject
    public RegisterPresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor,
                                 RegisterInteractor registerInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
        mRegisterInteractor = registerInteractor;
    }

    @Override
    public void setRegisterView(RegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void register(RegisterParams registerParams) {

    }

    @Override
    public void cancelRegister() {

    }
}
