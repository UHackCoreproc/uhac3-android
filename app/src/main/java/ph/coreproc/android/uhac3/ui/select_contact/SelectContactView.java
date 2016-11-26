package ph.coreproc.android.uhac3.ui.select_contact;

import java.util.List;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.models.Contact;

/**
 * Created by johneris on 26/11/2016.
 */

public interface SelectContactView {

    void showLoadingContactList();

    void showContactList(List<Contact> contactList);


    void showGetAccountListOfContactInProgress();

    void showAccountListOfContact(List<Account> accountList);

    void showGetAccountListOfContactError(ErrorBundle errorBundle);

    void showGetAccountListOfContactCancelled();
}
