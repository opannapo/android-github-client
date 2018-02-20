package napodev.app.test.views.activities.repo;

import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class RepoWorker extends BaseActivityControl implements RepoView.WorkerImpl {
    RepoView.ViewImpl view;

    public RepoWorker(BaseViewModel viewModel) {
        super(viewModel);
        this.view = (RepoView.ViewImpl) getViewModel().getViewImpl();
    }
}