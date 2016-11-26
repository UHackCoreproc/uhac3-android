package ph.coreproc.android.uhac3.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.params.RegisterParams;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.main.MainActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterActivity extends BaseActivity implements RegisterView {

    private static String ARGS_MOBILE_NUMBER = "ARGS_MOBILE_NUMBER";
    private static String ARGS_VERIFICATION_CODE = "ARGS_VERIFICATION_CODE";

    public static Intent newIntent(Context context, String mobileNumber, String verificationCode) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(ARGS_MOBILE_NUMBER, mobileNumber);
        intent.putExtra(ARGS_VERIFICATION_CODE, verificationCode);
        return intent;
    }

    @BindView(R.id.emailEditText)
    EditText mEmailEditText;

    @BindView(R.id.accountNoEditText)
    EditText mAccountNoEditText;

    @BindView(R.id.contactNoEditText)
    EditText mContactNoEditText;

    @BindView(R.id.passwordEditText)
    EditText mPasswordEditText;

    @BindView(R.id.confirmPasswordEditText)
    EditText mConfirmPasswordEditText;

    @Inject
    RegisterPresenter mRegisterPresenter;

    private String mMobileNumber;
    private String mVerificationCode;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        mMobileNumber = bundle.getString(ARGS_MOBILE_NUMBER);
        mVerificationCode = bundle.getString(ARGS_VERIFICATION_CODE);

        getApplicationComponent().inject(this);
        mRegisterPresenter.setRegisterView(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        mRegisterPresenter.setRegisterView(null);
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
     * {@link RegisterActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_register_title), true);
        mContactNoEditText.setText(mMobileNumber);
    }

    @OnClick(R.id.registerButton)
    public void onRegisterClicked() {
        String email = mEmailEditText.getText().toString();
        String accountNo = mAccountNoEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String confirmPassword = mConfirmPasswordEditText.getText().toString();

        String errorMessages = "";
        if (email.isEmpty()) {
            errorMessages = "Email is required.";
        }
        if (accountNo.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Account Number is required.";
        }
        if (password.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Password is required.";
        }
        if (confirmPassword.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Confirm Password is required.";
        }
        if (!errorMessages.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    errorMessages);
            return;
        }

        RegisterParams registerParams = new RegisterParams(email, accountNo,
                mMobileNumber, mVerificationCode, password, confirmPassword);
        mRegisterPresenter.register(registerParams);
    }

    /**
     * {@link RegisterView} Implementation
     */

    @Override
    public void showRegisterInProgress() {
        showProgressDialog(getString(R.string.register_progress),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mRegisterPresenter.cancelRegister();
                    }
                });
    }

    @Override
    public void showRegisterSuccess() {
        dismissProgressDialog();
        Intent intent = MainActivity.newIntent(mContext);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void showRegisterError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showRegisterCancelled() {
        dismissProgressDialog();
        Toast.makeText(mContext, getString(R.string.global_dialog_action_cancel),
                Toast.LENGTH_SHORT).show();
    }

}
