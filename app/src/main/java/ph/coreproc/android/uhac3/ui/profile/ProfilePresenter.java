package ph.coreproc.android.uhac3.ui.profile;

/**
 * Created by johneris on 09/11/2016.
 */

public interface ProfilePresenter {

    void setProfileView(ProfileView profileView);

    void getUser();

    void cancelGetUser();
}
