package napodev.app.test.views.fragments.branch;

import org.json.JSONException;

import java.util.ArrayList;

import napodev.app.test.entities.ContentEntity;
import napodev.app.test.utils.remote.API;
import napodev.app.test.utils.remote.ApiCallback;
import napodev.app.test.utils.remote.BaseResponseEntity;
import napodev.app.test.utils.remote.endpoint.EndPoint;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class FragmentBranchWorker extends BaseFragmentControl implements FragmentBranchView.WorkerImpl {
    FragmentBranchView.ViewImpl view;
    private boolean onProgress;

    public FragmentBranchWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentBranchView.ViewImpl) fragment.getViewModel().getViewImpl();
    }

    @Override
    public void reqRepoBranch(String username, String reponame) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.repo().branches(username, reponame))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess()) {
                            ArrayList<ContentEntity> entities = new ArrayList<>();
                            if (responseEntity.getDataArr().length() > 0) {
                                for (int i = 0; i < responseEntity.getDataArr().length(); i++) {
                                    try {
                                        ContentEntity r = new ContentEntity();
                                        r.setType("other");
                                        r.setName(responseEntity.getDataArr().getJSONObject(i).getString("name"));
                                        entities.add(r);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                view.resRepoBranch(true, "success", entities);
                            } else {
                                view.resRepoBranch(false, "No Data", null);
                            }
                        } else {
                            view.resRepoBranch(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resRepoBranch(false, info, null);
                    }
                })
                .exe();
    }
}
