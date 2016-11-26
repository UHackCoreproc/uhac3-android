package ph.coreproc.android.uhac3.data.net;

import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.inject.Singleton;

import ph.coreproc.android.uhac3.data.net.models.response.ApiHttpErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by johneris on 23/09/2016.
 */
@Singleton
public class ApiErrorUtil {

    private Retrofit mRetrofit;

    @Inject
    public ApiErrorUtil(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public ApiHttpErrorResponse parseError(Response<?> response) {
        Converter<ResponseBody, ApiHttpErrorResponse> converter =
                mRetrofit.responseBodyConverter(ApiHttpErrorResponse.class, new Annotation[0]);

        ApiHttpErrorResponse error;
        try {
            error = converter.convert(response.errorBody());
        } catch (Exception e) {
            ApiHttpErrorResponse apiHttpErrorResponse = new ApiHttpErrorResponse();
            ApiHttpErrorResponse.Error err = new ApiHttpErrorResponse.Error();
            err.setCode("" + response.code());
            err.setHttpCode(response.code());
            err.setMessage(response.message());
            apiHttpErrorResponse.setError(err);
            return apiHttpErrorResponse;
        }

        return error;
    }

}
