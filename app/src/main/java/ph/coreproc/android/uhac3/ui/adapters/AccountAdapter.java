package ph.coreproc.android.uhac3.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    public interface Callback {
        void onAccountClicked(Account delivery);
    }

    private List<Account> mAccountList;
    private Callback mCallback;

    private Context mContext;

    public AccountAdapter(List<Account> accountList, Callback callback) {
        mAccountList = accountList;
        mCallback = callback;
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_account, parent, false);
        AccountViewHolder accountViewHolder = new AccountViewHolder(view,
                new AccountViewHolder.Callback() {
                    @Override
                    public void onAccountClicked(int position) {
                        Account delivery = mAccountList.get(position);
                        mCallback.onAccountClicked(delivery);
                    }
                });
        mContext = parent.getContext();
        return accountViewHolder;
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Account account = mAccountList.get(position);

    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }

    public void changeData(List<Account> deliveryList) {
        mAccountList = deliveryList;
        notifyDataSetChanged();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.containerView)
        LinearLayout mContainerView;

        interface Callback {
            void onAccountClicked(int position);
        }

        private Callback mCallback;

        public AccountViewHolder(View itemView, Callback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCallback = callback;
            mContainerView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == mContainerView) {
                mCallback.onAccountClicked(getAdapterPosition());
            }
        }
    }

}
