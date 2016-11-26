package ph.coreproc.android.uhac3.domain.interactors;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by johneris on 23/09/2016.
 */
public class BaseInteractor {

    private Scheduler mExecutionThread;
    private Scheduler mPostExecutionThread;

    public BaseInteractor(Scheduler executionThread, Scheduler postExecutionThread) {
        mExecutionThread = executionThread;
        mPostExecutionThread = postExecutionThread;
    }

    protected <T> Observable.Transformer<T, T> applySchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(mExecutionThread)
                        .observeOn(mPostExecutionThread);
            }
        };
    }

}
