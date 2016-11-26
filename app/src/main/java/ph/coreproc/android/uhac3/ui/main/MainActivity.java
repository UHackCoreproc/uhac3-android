package ph.coreproc.android.uhac3.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;
import ph.coreproc.android.uhac3.ui.login.LoginActivity;

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
