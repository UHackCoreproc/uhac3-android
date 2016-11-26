package ph.coreproc.android.uhac3.ui.add_account;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public interface AddAccountView {

    void showAddAccountInProgress();

    void showAddAccountSuccess(Account account);

    void showAddAccountError(ErrorBundle errorBundle);

    void showAddAccountCancelled();
}
