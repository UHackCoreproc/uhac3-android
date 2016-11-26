package ph.coreproc.android.uhac3.ui.redeem_coupon;

import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;

/**
 * Created by johneris on 27/11/2016.
 */

public interface RedeemCouponPresenter {

    void setRedeemCouponView(RedeemCouponView redeemCouponView);

    void redeemCoupon(RedeemCouponParams redeemCouponParams);

    void cancelRedeemCoupon();
}
