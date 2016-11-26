package ph.coreproc.android.uhac3.models;

import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by johneris on 26/11/2016.
 */

public class Contact implements Comparable<Contact> {

    private String mContactId;
    private String mName;
    private String mPhoneNumber;

    private Uri mPhotoUri;

    public Contact(String contactId, String name,
                   String phoneNumber, Uri photoUri) {
        mContactId = contactId;
        mName = name;
        mPhoneNumber = phoneNumber;
        mPhotoUri = photoUri;
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

    @Nullable
    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        mPhotoUri = photoUri;
    }

    @Override
    public int compareTo(Contact another) {
        String thisName = mName.toUpperCase();
        String anotherName = another.mName.toUpperCase();
        return thisName.compareTo(anotherName);
    }

}
