package ph.coreproc.android.uhac3.ui.add_account;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.account.AddAccountInteractor;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public class AddAccountPresenterImpl implements AddAccountPresenter {

    private AddAccountView mAddAccountView;

    private AddAccountInteractor mAddAccountInteractor;

    @Inject
    public AddAccountPresenterImpl(AddAccountInteractor addAccountInteractor) {
        mAddAccountInteractor = addAccountInteractor;
    }

    @Override
    public void setAddAccountView(AddAccountView addAccountView) {
        mAddAccountView = addAccountView;
    }

    @Override
    public void addAccount(Account account) {
        if (mAddAccountView == null) {
            return;
        }

        mAddAccountView.showAddAccountInProgress();
        mAddAccountInteractor.addAccount(account, new AddAccountInteractor.Callback() {
            @Override
            public void onAddAccountSuccess(Account account) {
                if (mAddAccountView != null) {
                    mAddAccountView.showAddAccountSuccess(account);
                }
            }

            @Override
            public void onAddAccountError(ErrorBundle errorBundle) {
                if (mAddAccountView != null) {
                    mAddAccountView.showAddAccountError(errorBundle);
                }
            }

            @Override
            public void onAddAccountCancelled() {
                if (mAddAccountView != null) {
                    mAddAccountView.showAddAccountCancelled();
                }
            }
        });
    }

    @Override
    public void cancelAddAccount() {
        mAddAccountInteractor.cancelAddAccount();
    }
}
