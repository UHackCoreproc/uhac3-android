package ph.coreproc.android.appname.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;

import javax.inject.Inject;

import ph.coreproc.android.appname.R;
import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.User;
import ph.coreproc.android.appname.ui.BaseActivity;

/**
 * Created by johneris on 09/11/2016.
 */

public class ProfileActivity extends BaseActivity implements ProfileView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    @Inject
    ProfilePresenter mProfilePresenter;

    @Nullable
    private User mUser;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mProfilePresenter.setProfileView(this);

        initUI();
        mProfilePresenter.getUser();
    }

    @Override
    protected void onDestroy() {
        mProfilePresenter.setProfileView(null);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * {@link ProfileActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_title_profile), true);
    }

    /**
     * {@link ProfileView} Implementation
     */

    @Override
    public void showGetUserInProgress() {
        showProgressDialog(getString(R.string.global_dialog_message_please_wait));
    }

    @Override
    public void showGetUserSuccess(User user) {
        dismissProgressDialog();
        mUser = user;
    }

    @Override
    public void showGetUserError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setTitle(getString(R.string.global_dialog_title_error))
                .setMessage(errorBundle.getErrorMessage())
                .setPositiveButton(getString(R.string.global_dialog_action_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        })
                .create();
        showAlertDialog(alertDialog);
    }

    @Override
    public void showGetUserCancelled() {
        dismissProgressDialog();
    }

}
