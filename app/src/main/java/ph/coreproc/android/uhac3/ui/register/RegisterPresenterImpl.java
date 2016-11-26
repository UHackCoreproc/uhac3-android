package ph.coreproc.android.uhac3.ui.register;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.user.RegisterInteractor;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView mRegisterView;

    private RegisterInteractor mRegisterInteractor;

    @Inject
    public RegisterPresenterImpl(RegisterInteractor registerInteractor) {
        mRegisterInteractor = registerInteractor;
    }

    @Override
    public void setRegisterView(RegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void register(RegisterParams registerParams) {
        if (mRegisterView == null) {
            return;
        }

        mRegisterView.showRegisterInProgress();
        mRegisterInteractor.register(registerParams, new RegisterInteractor.Callback() {
            @Override
            public void onRegisterSuccess(User user) {
                if (mRegisterView != null) {
                    mRegisterView.showRegisterSuccess();
                }
            }

            @Override
            public void onRegisterError(ErrorBundle errorBundle) {
                if (mRegisterView != null) {
                    mRegisterView.showRegisterError(errorBundle);
                }
            }

            @Override
            public void onRegisterCancelled() {
                if (mRegisterView != null) {
                    mRegisterView.showRegisterCancelled();
                }
            }
        });
    }

    @Override
    public void cancelRegister() {
        mRegisterInteractor.cancelRegister();
    }
}
