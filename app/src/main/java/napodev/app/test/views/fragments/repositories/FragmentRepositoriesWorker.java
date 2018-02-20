package napodev.app.test.views.fragments.repositories;

import org.json.JSONException;

import java.util.ArrayList;

import napodev.app.test.entities.RepositoryEntity;
import napodev.app.test.utils.remote.API;
import napodev.app.test.utils.remote.ApiCallback;
import napodev.app.test.utils.remote.BaseResponseEntity;
import napodev.app.test.utils.remote.endpoint.EndPoint;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class FragmentRepositoriesWorker extends BaseFragmentControl implements FragmentRepositoriesView.WorkerImpl {
    FragmentRepositoriesView.ViewImpl view;
    private boolean onProgress;

    public FragmentRepositoriesWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentRepositoriesView.ViewImpl) fragment.getViewModel().getViewImpl();
    }


    @Override
    public void reqUserRepositories(String val) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.user().repositories(val))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess()) {
                            ArrayList<RepositoryEntity> entities = new ArrayList<>();
                            if (responseEntity.getDataArr().length() > 0) {

                                for (int i = 0; i < responseEntity.getDataArr().length(); i++) {
                                    try {
                                        RepositoryEntity r = (RepositoryEntity) new RepositoryEntity().parse(responseEntity.getDataArr().get(i));
                                        entities.add(r);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                view.resUserRepositories(true, "success", entities);
                            } else {
                                view.resUserRepositories(false, "No Data", null);
                            }
                        } else {
                            view.resUserRepositories(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resUserRepositories(false, info, null);
                    }
                })
                .exe();
    }
}
