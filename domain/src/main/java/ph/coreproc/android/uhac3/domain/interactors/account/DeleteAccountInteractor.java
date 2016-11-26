package ph.coreproc.android.uhac3.domain.interactors.account;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 27/11/2016.
 */

public interface DeleteAccountInteractor {

    interface Callback {
        void onDeleteAccountSuccess();

        void onDeleteAccountError(ErrorBundle errorBundle);

        void onDeleteAccountCancelled();
    }

    void deleteAccount(Account account, Callback callback);

    void cancelDeleteAccount();

}
