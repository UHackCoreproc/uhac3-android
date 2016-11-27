package ph.coreproc.android.uhac3.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.account_list.AccountListFragment;
import ph.coreproc.android.uhac3.ui.adapters.ViewPagerAdapter;
import ph.coreproc.android.uhac3.ui.add_account.AddAccountActivity;
import ph.coreproc.android.uhac3.ui.profile.ProfileActivity;
import ph.coreproc.android.uhac3.ui.redeem_coupon.RedeemCouponActivity;
import ph.coreproc.android.uhac3.ui.transaction_list.TransactionListFragment;

/**
 * Created by johneris on 09/10/2016.
 */

public class HomeActivity extends BaseActivity implements HomeView {

    private static String ARGS_SHOW_HISTORY = "ARGS_SHOW_HISTORY";

    public static Intent newIntent(Context context) {
        return newIntent(context, false);
    }

    public static Intent newIntent(Context context, boolean showHistory) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(ARGS_SHOW_HISTORY, showHistory);
        return intent;
    }

    @BindView(R.id.tabs)
    TabLayout mTabs;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    @Inject
    HomePresenter mHomePresenter;

    private ViewPagerAdapter mViewPagerAdapter;

    private boolean mShowHistory;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        mShowHistory = bundle.getBoolean(ARGS_SHOW_HISTORY, false);

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
        mHomePresenter.setHomeView(null);
        super.onDestroy();
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
                goToAddAccount();
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
        initViewPager(mViewpager);
        initTabs(mTabs, mViewpager);

        if (mShowHistory) {
            mViewpager.setCurrentItem(1);
        }
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
                            case R.id.redeemCouponMenuItem:
                                goToRedeemCoupon();
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
        CircleImageView circleImageView = (CircleImageView)
                headerView.findViewById(R.id.cirleImageView);

        User user = mHomePresenter.getLoggedInUser();
        if (user != null) {
            nameTextView.setText(user.getFirstName() != null && user.getLastName() != null ?
                    user.getFirstName() + " " + user.getLastName() : "");
            emailTextView.setText(user.getEmail() != null ? user.getEmail() : "");
            if (user.getAvatarUrl() != null) {
                Glide.with(mContext).load(user.getAvatarUrl()).into(circleImageView);
            }
        } else {
            logout();
        }
    }

    private void initViewPager(ViewPager viewPager) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(AccountListFragment.newInstance(),
                getString(R.string.fragment_account_list_title));
        mViewPagerAdapter.addFragment(TransactionListFragment.newInstance(),
                getString(R.string.fragment_transaction_list_title));
        viewPager.setAdapter(mViewPagerAdapter);
    }

    private void initTabs(TabLayout tabLayout, ViewPager viewpager) {
        tabLayout.setupWithViewPager(viewpager);
    }

    private void goToProfile() {
        Intent intent = ProfileActivity.newIntent(mContext);
        startActivity(intent);
    }

    private void goToRedeemCoupon() {
        Intent intent = RedeemCouponActivity.newIntent(mContext);
        startActivity(intent);
    }

    private void goToAddAccount() {
        Intent intent = AddAccountActivity.newIntent(mContext);
        startActivity(intent);
    }

}

