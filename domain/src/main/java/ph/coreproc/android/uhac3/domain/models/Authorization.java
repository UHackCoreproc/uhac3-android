package ph.coreproc.android.uhac3.domain.models;

/**
 * Created by johneris on 06/11/2016.
 */

public class Authorization {

    private String mApiKey;

    public Authorization(String apiKey) {
        mApiKey = apiKey;
    }

    public String getApiKey() {
        return mApiKey;
    }

    public void setApiKey(String apiKey) {
        mApiKey = apiKey;
    }
}
