package ph.coreproc.android.appname.domain.errors;

import java.io.IOException;

/**
 * Created by johneris on 23/09/2016.
 */
public class ErrorBundle extends Exception {

    private String message;

    public ErrorBundle(String message) {
        super(message);
    }

    public ErrorBundle(Throwable throwable) {
        super(throwable);
        if (throwable instanceof IOException) {
            message = "No Internet Connection";
        }
    }

    protected void setErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        if (message != null && !message.isEmpty()) {
            return message;
        }
        return super.getLocalizedMessage();
    }

}