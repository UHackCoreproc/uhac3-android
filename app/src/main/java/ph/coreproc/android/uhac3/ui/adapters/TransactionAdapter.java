package ph.coreproc.android.uhac3.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ph.coreproc.android.uhac3.R;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 26/11/2016.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    public interface Callback {
        void onTransactionClicked(Transaction transaction);
    }

    private List<Transaction> mTransactionList;
    private Callback mCallback;

    private Context mContext;

    public TransactionAdapter(List<Transaction> transactionList, Callback callback) {
        mTransactionList = transactionList;
        mCallback = callback;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_transaction, parent, false);
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view,
                new TransactionViewHolder.Callback() {
                    @Override
                    public void onTransactionClicked(int position) {
                        Transaction transaction = mTransactionList.get(position);
                        mCallback.onTransactionClicked(transaction);
                    }
                });
        mContext = parent.getContext();
        return transactionViewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        Transaction transaction = mTransactionList.get(position);
        holder.mSourceTextView.setText(transaction.getReferenceNumber());
    }

    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }

    public void changeData(List<Transaction> transactionList) {
        mTransactionList = transactionList;
        notifyDataSetChanged();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.containerView)
        LinearLayout mContainerView;

        @BindView(R.id.statusTextView)
        TextView mStatusTextView;

        @BindView(R.id.sourceTextView)
        TextView mSourceTextView;

        @BindView(R.id.recipientTextView)
        TextView mRecipientTextView;

        @BindView(R.id.amountTextView)
        TextView mAmountTextView;

        interface Callback {
            void onTransactionClicked(int position);
        }

        private Callback mCallback;

        public TransactionViewHolder(View itemView, Callback callback) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mCallback = callback;
            mContainerView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == mContainerView) {
                mCallback.onTransactionClicked(getAdapterPosition());
            }
        }
    }

}
