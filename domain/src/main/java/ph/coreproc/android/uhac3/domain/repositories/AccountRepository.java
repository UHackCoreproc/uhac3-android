package ph.coreproc.android.uhac3.domain.repositories;

import java.util.List;

import ph.coreproc.android.uhac3.domain.models.Account;
import ph.coreproc.android.uhac3.domain.models.User;
import rx.Observable;

/**
 * Created by johneris on 26/11/2016.
 */

public interface AccountRepository {

    /**
     * Get list of {@link Account} of currently logged in {@link User}
     *
     * @return
     */
    Observable<List<Account>> getAccountList();

}
