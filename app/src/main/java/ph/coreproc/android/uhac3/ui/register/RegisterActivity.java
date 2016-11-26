package ph.coreproc.android.uhac3.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.ui.BaseActivity;

/**
 * Created by johneris on 26/11/2016.
 */

public class RegisterActivity extends BaseActivity implements RegisterView {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Inject
    RegisterPresenter mRegisterPresenter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }


}
