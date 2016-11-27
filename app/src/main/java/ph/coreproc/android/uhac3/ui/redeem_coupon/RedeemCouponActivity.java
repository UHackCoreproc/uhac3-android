package ph.coreproc.android.uhac3.ui.redeem_coupon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.home.HomeActivity;

/**
 * Created by johneris on 27/11/2016.
 */

public class RedeemCouponActivity extends BaseActivity implements RedeemCouponView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RedeemCouponActivity.class);
        return intent;
    }

    @BindView(R.id.sourceMobileNoEditText)
    EditText mSourceMobileNoEditText;

    @BindView(R.id.recipientMobileNoEditText)
    EditText mRecipientMobileNoEditText;

    @BindView(R.id.couponCodeEditText)
    EditText mCouponCodeEditText;

    @Inject
    RedeemCouponPresenter mRedeemCouponPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_redeem_coupon;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mRedeemCouponPresenter.setRedeemCouponView(this);

        initUI();
    }

    @Override
    protected void onDestroy() {
        mRedeemCouponPresenter.setRedeemCouponView(null);
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
     * {@link RedeemCouponActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_redeem_coupon_title), true);
    }

    @OnClick(R.id.redeemAccountButton)
    public void onRedeemClicked() {
        String sourceMobileNo = mSourceMobileNoEditText.getText().toString();
        String recipientMobileNo = mRecipientMobileNoEditText.getText().toString();
        String couponCode = mCouponCodeEditText.getText().toString();

        String errorMessages = "";
        if (sourceMobileNo.isEmpty()) {
            errorMessages = "Source mobile number is required.";
        }
        if (recipientMobileNo.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Recipient mobile number is required.";
        }
        if (couponCode.isEmpty()) {
            errorMessages += (errorMessages.isEmpty() ? "" : "\n");
            errorMessages += "Coupon Code is required.";
        }
        if (!errorMessages.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    errorMessages);
            return;
        }

        RedeemCouponParams redeemCouponParams = new RedeemCouponParams(sourceMobileNo,
                recipientMobileNo, couponCode);
        mRedeemCouponPresenter.redeemCoupon(redeemCouponParams);
    }

    /**
     * {@link RedeemCouponView} Implementation
     */

    @Override
    public void showRedeemCouponInProgress() {
        showProgressDialog(getString(R.string.global_dialog_message_please_wait),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mRedeemCouponPresenter.cancelRedeemCoupon();
                    }
                });
    }

    @Override
    public void showRedeemCouponSuccess(Transaction transaction) {
        dismissProgressDialog();
        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setTitle(getString(R.string.global_dialog_title_success))
                .setMessage("Successful Transaction!")
                .setPositiveButton(getString(R.string.global_dialog_action_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = HomeActivity.newIntent(mContext, true);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                .create();
        showAlertDialog(alertDialog);
    }

    @Override
    public void showRedeemCouponError(ErrorBundle errorBundle) {
        dismissProgressDialog();
        errorBundle = handleGlobalError(errorBundle);
        showAlertDialog(getString(R.string.global_dialog_title_error),
                errorBundle.getErrorMessage());
    }

    @Override
    public void showRedeemCouponCancelled() {
        dismissProgressDialog();
        Toast.makeText(mContext, getString(R.string.global_toast_cancelled),
                Toast.LENGTH_SHORT).show();
    }
}
