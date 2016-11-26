package ph.coreproc.android.uhac3.domain.interactors.account;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public interface GetAccountListInteractor {

    interface Callback {
        void onGetAccountListSuccess(List<Account> accountList);

        void onGetAccountListError(ErrorBundle errorBundle);

        void onGetAccountListCancelled();
    }

    void getAccountList(Callback callback);

    void cancelGetAccountList();

}
