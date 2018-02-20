package napodev.app.test.views.fragments.following;

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

public class FragmentFollowingWorker extends BaseFragmentControl implements FragmentFollowingView.WorkerImpl {
    FragmentFollowingView.ViewImpl view;
    private boolean onProgress;

    public FragmentFollowingWorker(BaseFragment fragment) {
        super(fragment);
        this.view = (FragmentFollowingView.ViewImpl) fragment.getViewModel().getViewImpl();
    }


    @Override
    public void reqUserFollowing(String val) {
        if (onProgress) return;

        onProgress = true;
        new API().init(EndPoint.user().following(val))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess() && responseEntity.getDataArr() != null) {
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

                                view.resUserFollowing(true, "success", entities);
                            } else {
                                view.resUserFollowing(false, "No Data", null);
                            }
                        } else {
                            view.resUserFollowing(false, responseEntity.getError(), null);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resUserFollowing(false, info, null);
                    }
                })
                .exe();
    }
}
