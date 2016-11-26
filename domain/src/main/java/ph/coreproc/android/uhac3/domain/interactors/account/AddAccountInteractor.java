package ph.coreproc.android.uhac3.domain.interactors.account;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public interface AddAccountInteractor {

    interface Callback {
        void onAddAccountSuccess(Account account);

        void onAddAccountError(ErrorBundle errorBundle);

        void onAddAccountCancelled();
    }

    void addAccount(Account account, Callback callback);

    void cancelAddAccount();

}
