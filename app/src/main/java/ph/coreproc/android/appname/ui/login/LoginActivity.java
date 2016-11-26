package ph.coreproc.android.appname.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import ph.coreproc.android.appname.domain.models.params.LoginParams;
import ph.coreproc.android.appname.ui.BaseActivity;
import ph.coreproc.android.appname.ui.home.HomeActivity;
import ph.coreproc.android.appname.R;

/**
 * Created by johneris on 06/11/2016.
 */

public class LoginActivity extends BaseActivity implements LoginView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @BindView(R.id.usernameEditText)
    EditText mUsernameEditText;

    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;

    @BindView(R.id.loginButton)
    Button mLoginButton;

    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mLoginPresenter.setLoginView(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        mLoginPresenter.setLoginView(null);
        super.onDestroy();
    }


    /**
     * {@link LoginActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_login_title));
    }

    @OnClick(R.id.loginButton)
    public void onLoginClicked() {
        String username = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        String errorMessages = "";
        if (username.isEmpty()) {
            errorMessages = "Username is required.";
        }
        if (password.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Password is required.";
        }
        if (!errorMessages.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    errorMessages);
            return;
        }

        LoginParams loginParams = new LoginParams(username, password);
        mLoginPresenter.login(loginParams);
    }


    /**
     * {@link LoginView} Implementation
     */

    @Override
    public void showLoginProgress() {
        showProgressDialog(getString(R.string.login_progress),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mLoginPresenter.cancelLogin();
                    }
                });
    }

    @Override
    public void showLoginSuccess() {
        dismissProgressDialog();
        Intent intent = HomeActivity.newIntent(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showLoginCancelled() {
        dismissProgressDialog();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled), Toast.LENGTH_SHORT).show();
    }

}
