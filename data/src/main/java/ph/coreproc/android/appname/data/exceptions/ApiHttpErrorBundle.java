package ph.coreproc.android.appname.data.exceptions;

import ph.coreproc.android.appname.domain.errors.ErrorBundle;
import retrofit2.Response;

/**
 * Used for {@link Response#isSuccessful() unsuccessful} API call
 * <p>
 * Created by johneris on 16/07/2016.
 */
public class ApiHttpErrorBundle extends ErrorBundle {

    private int code;
    private String message;

    public ApiHttpErrorBundle(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
