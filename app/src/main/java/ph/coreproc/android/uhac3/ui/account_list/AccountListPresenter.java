package ph.coreproc.android.uhac3.ui.account_list;

/**
 * Created by johneris on 26/11/2016.
 */

public interface AccountListPresenter {

    void setAccountListView(AccountListView accountListView);

    void getAccountList();

    void cancelGetAccountList();

}
