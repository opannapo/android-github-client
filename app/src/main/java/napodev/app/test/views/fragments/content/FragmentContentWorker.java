package napodev.app.test.views.fragments.content;

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

public class FragmentContentWorker extends BaseFragmentControl implements FragmentContentView.WorkerImpl {
    FragmentContentView.ViewImpl view;
    private boolean onProgress;

    public FragmentContentWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentContentView.ViewImpl) fragment.getViewModel().getViewImpl();
    }


    @Override
    public void reqRepoContent(String username, String reponame) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.repo().contents(username, reponame))
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
                                        ContentEntity r = (ContentEntity) new ContentEntity().parse(responseEntity.getDataArr().get(i));
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
