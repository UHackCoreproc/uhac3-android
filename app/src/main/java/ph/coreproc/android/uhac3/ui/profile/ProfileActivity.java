package ph.coreproc.android.uhac3.ui.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.ui.BaseActivity;

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

    @BindView(R.id.avatarImageView)
    ImageView mAvatarImageView;

    @BindView(R.id.mobileNumberTextView)
    TextView mMobileNumberTextView;

    @BindView(R.id.nameTextView)
    TextView mNameTextView;

    @BindView(R.id.emailTextView)
    TextView mEmailTextView;

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

        Glide.with(mContext).load(mUser.getAvatarUrl()).into(mAvatarImageView);
        mMobileNumberTextView.setText(mUser.getMobileNumber());
        mNameTextView.setText(mUser.getFirstName() + " " + mUser.getLastName());
        mEmailTextView.setText(mUser.getEmail());
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
