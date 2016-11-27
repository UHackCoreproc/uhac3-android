package ph.coreproc.android.uhac3.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.models.Contact;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;
import ph.coreproc.android.uhac3.ui.login.LoginActivity;
import ph.coreproc.android.uhac3.util.ContactsGetter;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by johneris on 06/11/2016.
 */

public class MainActivity extends BaseActivity implements MainView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mMainPresenter.setMainView(this);

        initUI();
        mMainPresenter.checkLoggedInUser();

        if (ContactsGetter.sContactList == null) {
            ContactsGetter.getContacts(mContext)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultSubscriber<List<Contact>>() {
                        @Override
                        public void onError(ErrorBundle errorBundle) {

                        }

                        @Override
                        public void onNext(List<Contact> contactList) {
                            Collections.sort(contactList);
                            ContactsGetter.sContactList = contactList;
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.setMainView(null);
        super.onDestroy();
    }

    /**
     * {@link MainActivity} Methods
     */

    private void initUI() {

    }

    /**
     * {@link MainView} Implementation
     */

    @Override
    public void showUserIsLoggedIn(User user) {
        Intent intent = HomeActivity.newIntent(mContext);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUserNotLoggedIn() {
        Intent intent = LoginActivity.newIntent(mContext);
        startActivity(intent);
        finish();
    }

}
