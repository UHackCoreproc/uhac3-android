package ph.coreproc.android.uhac3.ui.redeem_coupon;

import javax.inject.Inject;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import ph.coreproc.android.uhac3.domain.interactors.transaction.RedeemCouponInteractor;
import ph.coreproc.android.uhac3.domain.models.Transaction;
import ph.coreproc.android.uhac3.domain.models.params.RedeemCouponParams;

/**
 * Created by johneris on 27/11/2016.
 */

public class RedeemCouponPresenterImpl implements RedeemCouponPresenter {

    private RedeemCouponView mRedeemCouponView;

    private RedeemCouponInteractor mRedeemCouponInteractor;

    @Inject
    public RedeemCouponPresenterImpl(RedeemCouponInteractor redeemCouponInteractor) {
        mRedeemCouponInteractor = redeemCouponInteractor;
    }

    @Override
    public void setRedeemCouponView(RedeemCouponView redeemCouponView) {
        mRedeemCouponView = redeemCouponView;
    }

    @Override
    public void redeemCoupon(RedeemCouponParams redeemCouponParams) {
        if (mRedeemCouponView == null) {
            return;
        }

        mRedeemCouponView.showRedeemCouponInProgress();
        mRedeemCouponInteractor.redeemCoupon(redeemCouponParams, new RedeemCouponInteractor.Callback() {
            @Override
            public void onRedeemCouponSuccess(Transaction transaction) {
                if (mRedeemCouponView != null) {
                    mRedeemCouponView.showRedeemCouponSuccess(transaction);
                }
            }

            @Override
            public void onRedeemCouponError(ErrorBundle errorBundle) {
                if (mRedeemCouponView != null) {
                    mRedeemCouponView.showRedeemCouponError(errorBundle);
                }
            }

            @Override
            public void onRedeemCouponCancelled() {
                if (mRedeemCouponView != null) {
                    mRedeemCouponView.showRedeemCouponCancelled();
                }
            }
        });
    }

    @Override
    public void cancelRedeemCoupon() {
        mRedeemCouponInteractor.cancelRedeemCoupon();
    }
}
