package ph.coreproc.android.uhac3.models;

/**
 * Created by johneris on 26/11/2016.
 */

public class Contact {

    private String mContactId;
    private String mName;
    private String mPhoneNumber;

    public Contact(String contactId, String name, String phoneNumber) {
        mContactId = contactId;
        mName = name;
        mPhoneNumber = phoneNumber;
    }

    public String getContactId() {
        return mContactId;
    }

    public void setContactId(String contactId) {
        mContactId = contactId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }
}
