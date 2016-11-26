package ph.coreproc.android.uhac3.ui.select_contact;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.models.Contact;
import ph.coreproc.android.uhac3.ui.BaseActivity;
import ph.coreproc.android.uhac3.ui.adapters.ContactAdapter;
import ph.coreproc.android.uhac3.ui.adapters.DividerItemDecoration;

/**
 * Created by johneris on 26/11/2016.
 */

public class SelectContactActivity extends BaseActivity implements SelectContactView, ContactAdapter.Callback {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SelectContactActivity.class);
        return intent;
    }

    @BindView(R.id.contactRecyclerView)
    RecyclerView mContactRecyclerView;

    @BindView(R.id.noContactTextView)
    TextView mNoContactTextView;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    SelectContactPresenter mSelectContactPresenter;

    private SearchView mSearchView;
    private ContactAdapter mContactAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_select_contact;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
        mSelectContactPresenter.setSelectContactView(this);

        initUI();
        mSelectContactPresenter.getContactList();
    }

    @Override
    protected void onDestroy() {
        mSelectContactPresenter.setSelectContactView(null);
        super.onDestroy();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_select_contacts, menu);

        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.searchViewMenuItem));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSelectContactPresenter.getContactList(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mSelectContactPresenter.getContactList(newText);
                return true;
            }
        });

        return true;
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
     * {@link SelectContactActivity} Methods
     */

    private void initUI() {
        initToolbar(getString(R.string.activity_select_contact_title), true);
        initRecyclerView(mContactRecyclerView);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        List<Contact> contactList = new ArrayList<>();
        mContactAdapter = new ContactAdapter(contactList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(mContactAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST)
        );
    }

    /**
     * {@link SelectContactView} Implementation
     */

    @Override
    public void showLoadingContactList() {
        mContactRecyclerView.setVisibility(View.GONE);
        mNoContactTextView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContactList(List<Contact> contactList) {
        mProgressBar.setVisibility(View.GONE);

        if (contactList.isEmpty()) {
            mContactAdapter.changeData(contactList);
            mContactRecyclerView.setVisibility(View.GONE);
            mNoContactTextView.setVisibility(View.VISIBLE);
        } else {
            mContactAdapter.changeData(contactList);
            mContactRecyclerView.setVisibility(View.VISIBLE);
            mNoContactTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showGetAccountListOfContactInProgress() {
        showProgressDialog(getString(R.string.select_contact_get_account_list_progress),
                new OnProgressDialogCancel() {
                    @Override
                    public void onCancel() {
                        mSelectContactPresenter.cancelGetAccountListOfContact();
                    }
                });
    }

    @Override
    public void showAccountListOfContact(List<Account> accountList) {
        dismissProgressDialog();
    }

    @Override
    public void showGetAccountListOfContactError(ErrorBundle errorBundle) {
        dismissProgressDialog();
    }

    @Override
    public void showGetAccountListOfContactCancelled() {
        dismissProgressDialog();
    }

    @Override
    public void onContactClicked(Contact contact) {
        mSelectContactPresenter.getAccountListOfContact(contact);
    }
}
