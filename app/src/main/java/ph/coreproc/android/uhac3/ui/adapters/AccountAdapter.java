package ph.coreproc.android.uhac3.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.Account;

/**
 * Created by johneris on 26/11/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    public interface Callback {
        void onAccountClicked(Account account);
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
                        Account account = mAccountList.get(position);
                        mCallback.onAccountClicked(account);
                    }
                });
        mContext = parent.getContext();
        return accountViewHolder;
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Account account = mAccountList.get(position);
        if (account.getAccountType().getId() == 1) {
            Glide.with(mContext).load(R.drawable.ic_unionbank).into(holder.mImageView);
        } else {
            Glide.with(mContext).load(R.drawable.ic_credit_card).into(holder.mImageView);
        }
        holder.mTitleTextView.setText(account.getTitle());
        holder.mAccountNoTextView.setText(account.getAccountNumber());
        holder.mAccountTypeTextView.setText(account.getAccountType().getName());
        if (account.getBalance() != null) {
            holder.mAmountTextView.setText(toPesoFormat(account.getBalance()));
        }
    }

    public String toPesoFormat(double peso) {
        return "₱" + new DecimalFormat("#,###,##0.00").format(peso);
    }

    @Override
    public int getItemCount() {
        return mAccountList.size();
    }

    public void changeData(List<Account> accountList) {
        mAccountList = accountList;
        notifyDataSetChanged();
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.containerView)
        LinearLayout mContainerView;

        @BindView(R.id.imageView)
        CircleImageView mImageView;

        @BindView(R.id.titleTextView)
        TextView mTitleTextView;

        @BindView(R.id.accountNoTextView)
        TextView mAccountNoTextView;

        @BindView(R.id.accountTypeTextView)
        TextView mAccountTypeTextView;

        @BindView(R.id.amountTextView)
        TextView mAmountTextView;

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
