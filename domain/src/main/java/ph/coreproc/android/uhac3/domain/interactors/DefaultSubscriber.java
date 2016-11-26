package ph.coreproc.android.uhac3.domain.interactors;

import ph.coreproc.android.uhac3.domain.errors.ErrorBundle;
import rx.Subscriber;

/**
 * Created by johneris on 23/09/2016.
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    public abstract void onError(ErrorBundle errorBundle);

    @Override
    public void onCompleted() {
        // do nothing
    }

    @Override
    public void onError(Throwable e) {
        ErrorBundle errorBundle;
        if (e instanceof ErrorBundle) {
            errorBundle = (ErrorBundle) e;
        } else {
            errorBundle = new ErrorBundle(e);
        }
        onError(errorBundle);
    }

    @Override
    public void onNext(T r) {
        // do nothing
    }

}
