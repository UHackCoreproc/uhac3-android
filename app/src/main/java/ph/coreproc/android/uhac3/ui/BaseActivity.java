package ph.coreproc.android.uhac3.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ph.coreproc.android.uhac3.App;
import ph.coreproc.android.uhac3.data.exceptions.ApiHttpErrorBundle;
import ph.coreproc.android.uhac3.dependency_injection.components.ApplicationComponent;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.user.LogoutInteractor;
import ph.coreproc.android.uhac3.errors.UserNotLoggedInError;
import ph.coreproc.android.uhac3.ui.main.MainActivity;
import ph.coreproc.android.uhac3.R;

/**
 * Created by johneris on 23/09/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected Toolbar mToolbar;
    private TextView mToolbarTitleTextView;

    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;

    @Inject
    LogoutInteractor mLogoutInteractor;

    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        mContext = this;

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitleTextView = (TextView) findViewById(R.id.toolbarTitleTextView);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
        }

        getApplicationComponent().inject(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;

        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }

        mAlertDialog = null;
    }

    @Override
    protected void onDestroy() {
        mContext = null;
        super.onDestroy();
    }


    protected static Gson getGsonForBundle() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    protected void initToolbar(String toolbarTitle) {
        initToolbar(toolbarTitle, false);
    }

    protected void initToolbar(String toolbarTitle, boolean setDisplayHomeAsUpEnabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled);
        }
        if (mToolbarTitleTextView != null) {
            mToolbarTitleTextView.setText(toolbarTitle);
        }
    }


    protected interface OnProgressDialogCancel {
        void onCancel();
    }

    /**
     * Use this method to show a {@link ProgressDialog} instead of creating
     * a new one inside your {@link android.app.Activity}. This {@link ProgressDialog}
     * will be handled in {@link #onStop()} to prevent memory leaks from {@link ProgressDialog}
     * that were not dismissed after the {@link android.app.Activity} has been destroyed.
     *
     * @param title
     * @param message
     */
    protected void showProgressDialog(String title, String message, boolean cancellable,
                                      final OnProgressDialogCancel onProgressDialogCancel) {
        if (mContext == null) {
            return;
        }
        dismissProgressDialog();

        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(cancellable);
        if (onProgressDialogCancel != null) {
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                    "Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onProgressDialogCancel.onCancel();
                        }
                    });
        }
        try {
            mProgressDialog.show();
        } catch (Exception e) {
        }
    }

    protected void showProgressDialog(String message) {
        showProgressDialog("", message, false, null);
    }

    protected void showProgressDialog(String message, OnProgressDialogCancel onProgressDialogCancel) {
        showProgressDialog("", message, false, onProgressDialogCancel);
    }

    protected void updateProgressDialogMessage(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
        } else {
            showProgressDialog(message);
        }
    }

    protected void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = null;
    }

    protected void dismissAlertDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
        mAlertDialog = null;
    }


    /**
     * Use this to show your {@link AlertDialog} instead of creating
     * a new one inside your {@link android.app.Activity}. This {@link AlertDialog}
     * will be handled in {@link #onStop()} to prevent memory leaks from {@link AlertDialog}
     * that were not dismissed after the {@link android.app.Activity} has been destroyed.
     *
     * @param alertDialog
     */
    protected void showAlertDialog(AlertDialog alertDialog) {
        dismissAlertDialog();
        mAlertDialog = alertDialog;
        mAlertDialog.show();
    }

    protected void showAlertDialog(String title, String message, boolean cancelable) {
        if (mContext == null) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                .create();
        showAlertDialog(alertDialog);
    }

    protected void showAlertDialog(String title, String message) {
        showAlertDialog(title, message, false);
    }

    protected void showAlertDialog(String message) {
        showAlertDialog("", message, false);
    }


    /**
     * Receive {@link ErrorBundle error bundle} and do necessary actions
     *
     * @param errorBundle
     */
    public ErrorBundle handleGlobalError(ErrorBundle errorBundle) {
        if (errorBundle instanceof UserNotLoggedInError) {
            logout();
            return errorBundle;
        }
        if (errorBundle instanceof ApiHttpErrorBundle) {
            ApiHttpErrorBundle apiHttpErrorBundle = (ApiHttpErrorBundle) errorBundle;
            if (apiHttpErrorBundle.getCode() == 401) {
                logout();
                return errorBundle;
            }
        }
        return errorBundle;
    }

    protected void logout() {
        mLogoutInteractor.logout();
        Intent intent = MainActivity.newIntent(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
