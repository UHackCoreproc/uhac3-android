package ph.coreproc.android.uhac3.domain.interactors.util;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public interface GetAccountListOfContactInteractor {

    interface Callback {
        void onGetAccountListOfContactSuccess(List<Account> accountList);

        void onGetAccountListOfContactError(ErrorBundle errorBundle);

        void onGetAccountListOfContactCancelled();
    }

    void getAccountListOfContact(String mobileNumber, Callback callback);

    void cancelgetAccountListOfContact();

}
