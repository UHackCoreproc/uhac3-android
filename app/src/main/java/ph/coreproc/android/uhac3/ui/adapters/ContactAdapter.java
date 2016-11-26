package ph.coreproc.android.uhac3.ui.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.models.Contact;

/**
 * Created by johneris on 26/11/2016.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    public interface Callback {
        void onContactClicked(Contact contact);
    }

    private List<Contact> mContactList;
    private Callback mCallback;

    private Context mContext;

    public ContactAdapter(List<Contact> contactList, Callback callback) {
        mContactList = contactList;
        mCallback = callback;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view,
                new ContactViewHolder.Callback() {
                    @Override
                    public void onContactClicked(int position) {
                        Contact contact = mContactList.get(position);
                        mCallback.onContactClicked(contact);
                    }
                });
        mContext = parent.getContext();
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = mContactList.get(position);
        holder.mNameTextView.setText(contact.getName());
        holder.mPhoneNumberTextView.setText(contact.getPhoneNumber());
        Uri imageUri = contact.getPhotoUri();
        if (imageUri == null) {
            Glide.with(mContext).load(R.drawable.contact_placeholder).into(holder.mPhotoImageView);
        } else {
            Glide.with(mContext).load(imageUri).into(holder.mPhotoImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public void changeData(List<Contact> contactList) {
        mContactList = contactList;
        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.containerView)
        LinearLayout mContainerView;

        @BindView(R.id.photoImageView)
        ImageView mPhotoImageView;

        @BindView(R.id.nameTextView)
        TextView mNameTextView;

        @BindView(R.id.phoneNumberTextView)
        TextView mPhoneNumberTextView;

        interface Callback {
            void onContactClicked(int position);
        }

        private Callback mCallback;

        public ContactViewHolder(View itemView, Callback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCallback = callback;
            mContainerView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == mContainerView) {
                mCallback.onContactClicked(getAdapterPosition());
            }
        }
    }

}
