package ph.coreproc.android.uhac3.ui.add_account;

import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public interface AddAccountPresenter {

    void setAddAccountView(AddAccountView addAccountView);

    void addAccount(Account account);

    void cancelAddAccount();

}
