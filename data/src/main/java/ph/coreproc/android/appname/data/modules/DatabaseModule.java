package ph.coreproc.android.appname.data.modules;

import dagger.Module;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class DatabaseModule {

    private String mDatabaseName;

    public DatabaseModule(String databaseName) {
        mDatabaseName = databaseName;
    }

    /*@Singleton
    @Provides
    SQLiteDatabase provideDatabase(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, mDatabaseName, null);
        SQLiteDatabase database = helper.getWritableDatabase();
        return database;
    }

    @Singleton
    @Provides
    DaoMaster provideDaoMaster(SQLiteDatabase database) {
        return new DaoMaster(database);
    }

    @Singleton
    @Provides
    DaoSession provideDaoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }*/

}