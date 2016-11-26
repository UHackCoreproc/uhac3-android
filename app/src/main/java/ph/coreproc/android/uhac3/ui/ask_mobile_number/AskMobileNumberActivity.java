package ph.coreproc.android.uhac3.ui.ask_mobile_number;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.verify_mobile_number.VerifyMobileNumberActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class AskMobileNumberActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AskMobileNumberActivity.class);
        return intent;
    }

    @BindView(R.id.mobileNumber)
    EditText mMobileNumber;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ask_mobile_number;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();
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

    private void initUI() {
        initToolbar(getString(R.string.activity_ask_mobile_number_title), true);
    }

    @OnClick(R.id.proceedButton)
    public void onProceedClicked() {
        String mobileNumber = mMobileNumber.getText().toString();

        if (mobileNumber.isEmpty()) {
            showAlertDialog(getString(R.string.global_dialog_title_error),
                    "Please enter your mobile number.");
            return;
        }

        Intent intent = VerifyMobileNumberActivity.newIntent(mContext, mobileNumber);
        startActivity(intent);
    }

}
