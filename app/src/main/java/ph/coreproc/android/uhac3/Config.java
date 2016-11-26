package ph.coreproc.android.uhac3;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by johneris on 23/09/2016.
 */
public class Config {

    // MARK: - API
    public static final String BASE_URL_LIVE = "https://oceanictracking.herokuapp.com/";
    public static final String BASE_URL_DEV = "https://oceanictracking.herokuapp.com/";
    // MARK: - End API


    // MARK: Database
    /**
     * versions
     * v0 - Development
     */
    public static final String DATABASE_NAME = "app-db-v0";
    // MARK: End Database


    // MARK: Preferences
    public static final String PREFERENCES_PACKAGE = "ph.coreproc.android.oceanic";
    // MARK: End Preferences


    // MARK: Schedulers
    public static Scheduler EXECUTION_THREAD = Schedulers.io();
    public static Scheduler POST_EXECUTION_THREAD = AndroidSchedulers.mainThread();
    // MARK: End Schedulers

}
