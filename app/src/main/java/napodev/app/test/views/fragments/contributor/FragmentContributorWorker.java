package napodev.app.test.views.fragments.contributor;

import org.json.JSONException;

import java.util.ArrayList;

import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.remote.API;
import napodev.app.test.utils.remote.ApiCallback;
import napodev.app.test.utils.remote.BaseResponseEntity;
import napodev.app.test.utils.remote.endpoint.EndPoint;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class FragmentContributorWorker extends BaseFragmentControl implements FragmentContributorView.WorkerImpl {
    FragmentContributorView.ViewImpl view;
    private boolean onProgress;

    public FragmentContributorWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentContributorView.ViewImpl) fragment.getViewModel().getViewImpl();
    }


    @Override
    public void reqRepoContent(String username, String reponame) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.repo().contributors(username, reponame))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess()) {
                            ArrayList<UserEntity> entities = new ArrayList<>();
                            if (responseEntity.getDataArr().length() > 0) {

                                for (int i = 0; i < responseEntity.getDataArr().length(); i++) {
                                    try {
                                        UserEntity r = (UserEntity) new UserEntity().parse(responseEntity.getDataArr().get(i));
                                        entities.add(r);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                view.resRepoContent(true, "success", entities);
                            } else {
                                view.resRepoContent(false, "No Data", null);
                            }
                        } else {
                            view.resRepoContent(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resRepoContent(false, info, null);
                    }
                })
                .exe();
    }
}
