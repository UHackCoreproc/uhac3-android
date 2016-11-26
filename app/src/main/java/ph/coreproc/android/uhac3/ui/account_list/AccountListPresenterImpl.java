package ph.coreproc.android.uhac3.ui.account_list;

import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.account.GetAccountListInteractor;
import ph.coreproc.android.uhac3.domain.interactors.user.GetLoggedInUserInteractor;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public class AccountListPresenterImpl implements AccountListPresenter {

    private AccountListView mAccountListView;

    private GetLoggedInUserInteractor mGetLoggedInUserInteractor;
    private GetAccountListInteractor mGetAccountListInteractor;

    @Inject
    public AccountListPresenterImpl(GetLoggedInUserInteractor getLoggedInUserInteractor,
                                    GetAccountListInteractor getAccountListInteractor) {
        mGetLoggedInUserInteractor = getLoggedInUserInteractor;
        mGetAccountListInteractor = getAccountListInteractor;
    }

    @Override
    public void setAccountListView(AccountListView accountListView) {
        mAccountListView = accountListView;
    }

    @Override
    public void getAccountList() {
        if (mAccountListView == null) {
            return;
        }

        mAccountListView.showLoadingAccountList();
        mGetAccountListInteractor.getAccountList(new GetAccountListInteractor.Callback() {
            @Override
            public void onGetAccountListSuccess(List<Account> accountList) {
                if (mAccountListView != null) {
                    mAccountListView.showLoadingAccountListSuccess(accountList);
                }
            }

            @Override
            public void onGetAccountListError(ErrorBundle errorBundle) {
                if (mAccountListView != null) {
                    mAccountListView.showLoadingAccountListError(errorBundle);
                }
            }

            @Override
            public void onGetAccountListCancelled() {
                if (mAccountListView != null) {
                    mAccountListView.showLoadingAccountListCancelled();
                }
            }
        });
    }

    @Override
    public void cancelGetAccountList() {
        mGetAccountListInteractor.cancelGetAccountList();
    }
}
