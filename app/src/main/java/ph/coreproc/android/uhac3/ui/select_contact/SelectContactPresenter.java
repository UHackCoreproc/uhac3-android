package ph.coreproc.android.uhac3.ui.select_contact;

import ph.coreproc.android.uhac3.models.Contact;

/**
 * Created by johneris on 26/11/2016.
 */

public interface SelectContactPresenter {

    void setSelectContactView(SelectContactView selectContactView);

    void getContactList();

    void getContactList(String query);

    void getAccountListOfContact(Contact contact);

    void cancelGetAccountListOfContact();

}
