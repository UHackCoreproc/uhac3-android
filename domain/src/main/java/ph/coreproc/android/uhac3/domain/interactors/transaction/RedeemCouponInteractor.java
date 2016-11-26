package ph.coreproc.android.uhac3.domain.interactors.transaction;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;

/**
 * Created by johneris on 26/11/2016.
 */

public interface RedeemCouponInteractor {

    interface Callback {
        void onRedeemCouponSuccess(Transaction transaction);

        void onRedeemCouponError(ErrorBundle errorBundle);

        void onRedeemCouponCancelled();
    }

    void redeemCoupon(RedeemCouponParams redeemCouponParams, Callback callback);

    void cancelRedeemCoupon();

}
