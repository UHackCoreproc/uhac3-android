package ph.coreproc.android.appname.data.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ph.coreproc.android.appname.data.net.ApiErrorUtil;
import ph.coreproc.android.appname.data.net.ApiService;
import ph.coreproc.android.appname.data.net.AuthorizationInterceptor;
import ph.coreproc.android.appname.data.net.ResponseDataTypeAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johneris on 23/09/2016.
 */
@Module
public class NetModule {

    String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapterFactory(new ResponseDataTypeAdapterFactory())
                .create();
        return gson;
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    @Named("AuthorizationInterceptor")
    Interceptor provideAuthorizationInterceptor(AuthorizationInterceptor authorizationInterceptor) {
        return authorizationInterceptor;
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                     @Named("AuthorizationInterceptor") Interceptor authorizationInterceptor) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authorizationInterceptor)
                .build();
        return httpClient;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    ApiErrorUtil provideApiErrorUtil(Retrofit retrofit) {
        return new ApiErrorUtil(retrofit);
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
