package napodev.app.test.views.activities.home;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.remote.API;
import napodev.app.test.utils.remote.ApiCallback;
import napodev.app.test.utils.remote.BaseResponseEntity;
import napodev.app.test.utils.remote.GetFields;
import napodev.app.test.utils.remote.endpoint.EndPoint;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;
import napodev.framework.bework.utils.helper.JSONHelper;

public class HomeWorker extends BaseActivityControl implements HomeView.WorkerImpl {
    HomeView.ViewImpl view;
    private boolean onProgress;

    public HomeWorker(BaseViewModel viewModel) {
        super(viewModel);
        this.view = (HomeView.ViewImpl) getViewModel().getViewImpl();
    }

    @Override
    public void reqSearch(String val, final int page) {
        if (onProgress) return;
        onProgress = true;


        GetFields post = new GetFields();
        post.put("page", String.valueOf(page));
        post.put("q", val);
        post.put("per_page", "20");
        /*post.put("sort", "joined");
        post.put("order", "asc");*/
        post.print();

        new API().init(EndPoint.search().users(post))
                .executeCallback(new ApiCallback() {
                    @Override
                    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {
                        super.onResponseBeworkParcel(url, responseEntity);
                        onProgress = false;

                        if (responseEntity.isSuccess()) {
                            ArrayList<UserEntity> userEntities = new ArrayList<>();
                            if (responseEntity.getData().has("items")) {
                                int total_count = JSONHelper.getInt(responseEntity.getData(), "total_count");
                                JSONArray items = JSONHelper.getArray(responseEntity.getData(), "items");
                                for (int i = 0; i < items.length(); i++) {
                                    try {
                                        UserEntity u = (UserEntity) new UserEntity().parse(items.get(i));
                                        userEntities.add(u);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                view.resSearch(true, "success", userEntities, page, total_count);
                            } else {
                                view.resSearch(false, "No Data", null, page, 0);
                            }
                        } else {
                            view.resSearch(false, responseEntity.getError(), null, page, 0);
                        }
                    }

                    @Override
                    public void onResponseError(String url, String info) {
                        super.onResponseError(url, info);
                        onProgress = false;
                        view.resSearch(false, info, null, page, 0);
                    }
                })
                .exe();
    }
}