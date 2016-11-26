package ph.coreproc.android.uhac3.ui.verify_mobile_number;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.register.RegisterActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class VerifyMobileNumberActivity extends BaseActivity implements VerifyMobileNumberView {

    private static final String ARGS_MOBILE_NUMBER = "ARGS_MOBILE_NUMBER";

    public static Intent newIntent(Context context, String mobileNumber) {
        Intent intent = new Intent(context, VerifyMobileNumberActivity.class);
        intent.putExtra(ARGS_MOBILE_NUMBER, mobileNumber);
        return intent;
    }

    @BindView(R.id.verificationCodeEditText)
    EditText mVerificationCodeEditText;

    @Inject
    VerifyMobileNumberPresenter mVerifyMobileNumberPresenter;

    private String mMobileNumber;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_verify_mobile_number;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        mMobileNumber = bundle.getString(ARGS_MOBILE_NUMBER);

        getApplicationComponent().inject(this);
        mVerifyMobileNumberPresenter.setVerifyMobileNumberView(this);

        initUI();
        mVerifyMobileNumberPresenter.getVerificationCode(mMobileNumber);
    }

    @Override
    protected void onDestroy() {
        mVerifyMobileNumberPresenter.setVerifyMobileNumberView(null);
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
     * {@link VerifyMobileNumberActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_verify_mobile_number_title), true);
    }

    @OnClick(R.id.submitButton)
    public void onSubmitClicked() {
        String code = mVerificationCodeEditText.getText().toString().trim();

        if (code.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    "Please enter verification code.");
            return;
        }

        mVerifyMobileNumberPresenter.checkCode(code);
    }

    @OnClick(R.id.resendVerificationCodeButton)
    public void onResendVerificationCodeClicked() {
        mVerifyMobileNumberPresenter.getVerificationCode(mMobileNumber);
    }

    /**
     * {@link VerifyMobileNumberView} Implementation
     */

    @Override
    public void showVerifyMobileNumberInProgress() {
        showProgressDialog(getString(R.string.verify_mobile_number_progress),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mVerifyMobileNumberPresenter.cancelGetVerificationCode();
                    }
                });
    }

    @Override
    public void showVerifyMobileNumberSuccess() {
        dismissProgressDialog();
        showAlertDialog(getString(R.string.global_dialog_title_success),
                "A verification code has been sent in your mobile number.");
    }

    @Override
    public void showVerifyMobileNumberError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showVerifyMobileNumberCancelled() {
        dismissProgressDialog();
    }

    @Override
    public void showCheckCodeSuccess(String mobileNumber, String verificationCode) {
        Intent intent = RegisterActivity.newIntent(mContext, mobileNumber, verificationCode);
        startActivity(intent);
    }

    @Override
    public void showCheckCodeError(ErrorBundle errorBundle) {
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }
}
