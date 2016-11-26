package ph.coreproc.android.appname.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ph.coreproc.android.appname.domain.models.User;
import ph.coreproc.android.appname.ui.BaseActivity;
import ph.coreproc.android.appname.ui.profile.ProfileActivity;
import ph.coreproc.android.appname.R;

/**
 * Created by johneris on 09/10/2016.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Inject
    HomePresenter mHomePresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mHomePresenter.setHomeView(this);

        initUI();
    }

    @Override
    protected void onPause() {
        mNavigationView.setCheckedItem(R.id.homeMenuItem);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.setHomeView(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.profileMenuItem:
                goToProfile();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@link HomeActivity Methods}
     */

    private void initUI() {
        initToolbar();
        initNavigationView(mNavigationView);
    }

    private void initToolbar() {
        initToolbar(getString(R.string.activity_title_home));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initNavigationView(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        switch (item.getItemId()) {
                            case R.id.homeMenuItem:
                                break;
                            case R.id.profileMenuItem:
                                goToProfile();
                                break;
                            case R.id.logoutMenuItem:
                                logout();
                                break;
                        }
                        return true;
                    }
                });
        mNavigationView.setCheckedItem(R.id.homeMenuItem);

        View headerView = mNavigationView.getHeaderView(0);
        TextView nameTextView = (TextView)
                headerView.findViewById(R.id.nameTextView);
        TextView emailTextView = (TextView)
                headerView.findViewById(R.id.emailTextView);

        User user = mHomePresenter.getLoggedInUser();
        if (user != null) {
            nameTextView.setText("");
            emailTextView.setText("");
        } else {
            logout();
        }
    }

    private void goToProfile() {
        Intent intent = ProfileActivity.newIntent(mContext);
        startActivity(intent);
    }

}

