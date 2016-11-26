package ph.coreproc.android.uhac3.data.net;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.models.Authorization;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by johneris on 13/11/2016.
 */

public class AuthorizationInterceptor implements Interceptor {

    private UserRepository mUserRepository;

    @Inject
    public AuthorizationInterceptor(@Named("GetLoggedInUser") UserRepository userRepository) {
        mUserRepository = userRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        User user = mUserRepository.getLoggedInUser();

        Request.Builder requestBuilder = original.newBuilder();
        if (user != null && user.getAuthorization() != null) {
            Authorization authorization = user.getAuthorization();
            // add authorization headers
            requestBuilder = original.newBuilder()
                    .addHeader(ApiService.AUTHORIZATION, authorization.getApiKey());
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

}
