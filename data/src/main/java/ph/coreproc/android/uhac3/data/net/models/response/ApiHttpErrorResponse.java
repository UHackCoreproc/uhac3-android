package ph.coreproc.android.uhac3.data.net.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by johneris on 23/09/2016.
 */
public class ApiHttpErrorResponse {

    @SerializedName("error")
    private Error mError;

    public void setError(Error error) {
        mError = error;
    }

    public Error getError() {
        return mError;
    }

    public static class Error {

        @SerializedName("code")
        private String mCode;

        @SerializedName("http_code")
        private int mHttpCode;

        @SerializedName("message")
        private String mMessage;

        public String getCode() {
            return mCode;
        }

        public int getHttpCode() {
            return mHttpCode;
        }

        public String getMessage() {
            return mMessage;
        }

        public void setCode(String code) {
            mCode = code;
        }

        public void setHttpCode(int httpCode) {
            mHttpCode = httpCode;
        }

        public void setMessage(String message) {
            mMessage = message;
        }
    }
}
