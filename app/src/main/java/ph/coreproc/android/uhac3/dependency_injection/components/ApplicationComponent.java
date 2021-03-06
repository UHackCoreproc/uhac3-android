package ph.coreproc.android.uhac3.dependency_injection.components;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import ph.coreproc.android.uhac3.data.modules.DatabaseModule;
import ph.coreproc.android.uhac3.data.modules.NetModule;
import ph.coreproc.android.uhac3.data.modules.PreferencesModule;
import ph.coreproc.android.uhac3.data.modules.RepositoryModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.ApplicationModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.PresenterModule;
import ph.coreproc.android.uhac3.dependency_injection.modules.UtilModule;
import ph.coreproc.android.uhac3.domain.modules.InteractorModule;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.account_list.AccountListFragment;
import ph.coreproc.android.uhac3.ui.add_account.AddAccountActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;
import ph.coreproc.android.uhac3.ui.login.LoginActivity;
import ph.coreproc.android.uhac3.ui.main.MainActivity;
import ph.coreproc.android.uhac3.ui.profile.ProfileActivity;
import ph.coreproc.android.uhac3.ui.redeem_coupon.RedeemCouponActivity;
import ph.coreproc.android.uhac3.ui.register.RegisterActivity;
import ph.coreproc.android.uhac3.ui.select_contact.SelectContactActivity;
import ph.coreproc.android.uhac3.ui.transaction_list.TransactionListActivity;
import ph.coreproc.android.uhac3.ui.transaction_list.TransactionListFragment;
import ph.coreproc.android.uhac3.ui.transfer.TransferActivity;
import ph.coreproc.android.uhac3.ui.verify_mobile_number.VerifyMobileNumberActivity;

/**
 * Created by johneris on 23/09/2016.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class, PresenterModule.class, UtilModule.class, // app
        NetModule.class, DatabaseModule.class, PreferencesModule.class, RepositoryModule.class, // data
        InteractorModule.class // domain
})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(VerifyMobileNumberActivity verifyMobileNumberActivity);

    void inject(RegisterActivity registerActivity);

    void inject(HomeActivity homeActivity);

    void inject(AccountListFragment accountListFragment);

    void inject(TransactionListFragment transactionListFragment);

    void inject(TransactionListActivity transactionListActivity);

    void inject(ProfileActivity profileActivity);

    void inject(SelectContactActivity selectContactActivity);

    void inject(TransferActivity transferActivity);

    void inject(RedeemCouponActivity redeemCouponActivity);

    void inject(AddAccountActivity addAccountActivity);

    /**
     * Exposed to sub-graphs
     */

    // ApplicationModule
    Application application();
}
