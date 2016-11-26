package ph.coreproc.android.uhac3.ui.select_contact;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.DefaultSubscriber;
import ph.coreproc.android.uhac3.domain.interactors.util.GetAccountListOfContactInteractor;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.models.Contact;
import ph.coreproc.android.uhac3.util.ContactsGetter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by johneris on 26/11/2016.
 */

public class SelectContactPresenterImpl implements SelectContactPresenter {

    private SelectContactView mSelectContactView;

    private GetAccountListOfContactInteractor mGetAccountListOfContactInteractor;
    private Context mContext;

    @Nullable
    private List<Contact> mContactList;

    @Inject
    public SelectContactPresenterImpl(GetAccountListOfContactInteractor getAccountListOfContactInteractor,
                                      Context context) {
        mGetAccountListOfContactInteractor = getAccountListOfContactInteractor;
        mContext = context;
    }

    @Override
    public void setSelectContactView(SelectContactView selectContactView) {
        mSelectContactView = selectContactView;
    }

    @Override
    public void getContactList() {
        if (mSelectContactView == null) {
            return;
        }

        mSelectContactView.showLoadingContactList();
        ContactsGetter.getContacts(mContext)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<List<Contact>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {

                    }

                    @Override
                    public void onNext(List<Contact> contactList) {
                        Collections.sort(contactList);
                        mContactList = contactList;
                        mSelectContactView.showContactList(contactList);
                    }
                });
    }

    @Override
    public void getContactList(String query) {
        if (mSelectContactView == null) {
            return;
        }

        if (mContactList == null) {
            return;
        }

        if (query.isEmpty()) {
            mSelectContactView.showContactList(mContactList);
            return;
        }

        mSelectContactView.showLoadingContactList();
        filter(mContactList, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<List<Contact>>() {
                    @Override
                    public void onError(ErrorBundle errorBundle) {

                    }

                    @Override
                    public void onNext(List<Contact> contactList) {
                        mSelectContactView.showContactList(contactList);
                    }
                });
    }

    private Observable<List<Contact>> filter(final List<Contact> contactList, final String query) {
        return Observable.defer(new Func0<Observable<List<Contact>>>() {
            @Override
            public Observable<List<Contact>> call() {
                return Observable.just(getContactListFiltered(contactList, query));
            }
        });
    }

    private List<Contact> getContactListFiltered(List<Contact> contactList, String query) {
        List<Contact> contactListFiltered = new ArrayList<>();
        for (Contact contact : contactList) {
            if (contact.getName().toUpperCase().contains(query.toUpperCase())) {
                contactListFiltered.add(contact);
            }
        }
        return contactListFiltered;
    }


    @Override
    public void getAccountListOfContact(final Contact contact) {
        if (mSelectContactView == null) {
            return;
        }

        mSelectContactView.showGetAccountListOfContactInProgress();
        mGetAccountListOfContactInteractor.getAccountListOfContact(contact.getPhoneNumber(), new GetAccountListOfContactInteractor.Callback() {
            @Override
            public void onGetAccountListOfContactSuccess(List<Account> accountList) {
                if (mSelectContactView != null) {
                    mSelectContactView.showAccountListOfContact(accountList, contact);
                }
            }

            @Override
            public void onGetAccountListOfContactError(ErrorBundle errorBundle) {
                if (mSelectContactView != null) {
                    mSelectContactView.showGetAccountListOfContactError(errorBundle);
                }
            }

            @Override
            public void onGetAccountListOfContactCancelled() {
                if (mSelectContactView != null) {
                    mSelectContactView.showGetAccountListOfContactCancelled();
                }
            }
        });
    }

    @Override
    public void cancelGetAccountListOfContact() {
        mGetAccountListOfContactInteractor.cancelgetAccountListOfContact();
    }
}
