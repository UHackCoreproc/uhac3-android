package ph.coreproc.android.uhac3.ui.account_list;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public interface AccountListView {

    void showLoadingAccountList();

    void showLoadingAccountListSuccess(List<Account> accountList);

    void showLoadingAccountListError(ErrorBundle errorBundle);

    void showLoadingAccountListCancelled();

}
