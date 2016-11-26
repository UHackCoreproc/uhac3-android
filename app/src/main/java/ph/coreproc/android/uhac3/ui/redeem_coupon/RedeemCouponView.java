package ph.coreproc.android.uhac3.ui.redeem_coupon;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.models.Transaction;

/**
 * Created by johneris on 27/11/2016.
 */

public interface RedeemCouponView {

    void showRedeemCouponInProgress();

    void showRedeemCouponSuccess(Transaction transaction);

    void showRedeemCouponError(ErrorBundle errorBundle);

    void showRedeemCouponCancelled();

}
