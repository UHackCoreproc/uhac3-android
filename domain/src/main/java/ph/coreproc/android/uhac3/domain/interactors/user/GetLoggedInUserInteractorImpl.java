package ph.coreproc.android.uhac3.domain.interactors.user;

import javax.inject.Inject;
import javax.inject.Named;

import ph.coreproc.android.uhac3.domain.interactors.BaseInteractor;
import ph.coreproc.android.uhac3.domain.models.User;
import ph.coreproc.android.uhac3.domain.repositories.UserRepository;
import rx.Scheduler;

/**
 * Created by johneris on 09/10/2016.
 */

public class GetLoggedInUserInteractorImpl extends BaseInteractor
        implements GetLoggedInUserInteractor {

    private UserRepository mUserRepository;

    @Inject
    public GetLoggedInUserInteractorImpl(UserRepository userRepository,
                                         @Named("execution") Scheduler executionThread,
                                         @Named("post_execution") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mUserRepository = userRepository;
    }

    @Override
    public User getLoggedInUser() {
        return mUserRepository.getLoggedInUser();
    }
}
