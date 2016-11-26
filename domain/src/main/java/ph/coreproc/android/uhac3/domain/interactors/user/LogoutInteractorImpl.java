package ph.coreproc.android.uhac3.domain.interactors.user;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Scheduler;

/**
 * Created by johneris on 06/11/2016.
 */

public class LogoutInteractorImpl extends BaseInteractor implements LogoutInteractor {

    private UserRepository mUserRepository;

    @Inject
    public LogoutInteractorImpl(UserRepository userRepository,
                                @Named("execution") Scheduler executionThread,
                                @Named("post_execution") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mUserRepository = userRepository;
    }

    @Override
    public void logout() {
        mUserRepository.logout();
    }

}
