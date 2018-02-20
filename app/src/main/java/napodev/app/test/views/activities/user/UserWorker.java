package napodev.app.test.views.activities.user;

import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.remote.API;
import napodev.app.test.utils.remote.ApiCallback;
import napodev.app.test.utils.remote.BaseResponseEntity;
import napodev.app.test.utils.remote.endpoint.EndPoint;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;

public class UserWorker extends BaseActivityControl implements UserView.WorkerImpl {
    UserView.ViewImpl view;
    private boolean onProgress;

    public UserWorker(BaseViewModel viewModel) {
        super(viewModel);
        this.view = (UserView.ViewImpl) getViewModel().getViewImpl();
    }

    @Override
    public void reqUser(String val) {
        if (onProgress) return;
        onProgress = true;
        new API().init(EndPoint.user().username(val))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess()) {
                            UserEntity u = (UserEntity) new UserEntity().parse(responseEntity.getData());
                            view.resUser(true, "success", u);
                        } else {
                            view.resUser(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resUser(false, info, null);
                    }
                })
                .exe();
    }
}