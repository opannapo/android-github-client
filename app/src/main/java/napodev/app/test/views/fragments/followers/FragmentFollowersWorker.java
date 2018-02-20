package napodev.app.test.views.fragments.followers;

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

public class FragmentFollowersWorker extends BaseFragmentControl implements FragmentFollowersView.WorkerImpl {
    FragmentFollowersView.ViewImpl view;
    private boolean onProgress;

    public FragmentFollowersWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentFollowersView.ViewImpl) fragment.getViewModel().getViewImpl();
    }

    @Override
    public void reqUserFollowers(String val) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.user().followers(val))
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

                                view.resUserFollowers(true, "success", entities);
                            } else {
                                view.resUserFollowers(false, "No Data", null);
                            }
                        } else {
                            view.resUserFollowers(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resUserFollowers(false, info, null);
                    }
                })
                .exe();
    }
}
