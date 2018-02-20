package napodev.app.test.views.activities.home;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.adapter.users.UserAdapter;
import napodev.app.test.entities.GeneralFooterEntity;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.LoadingUtils;
import napodev.app.test.views.activities.user.UserActivity;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.adapter.NestedScrollViewListener;
import napodev.framework.bework.corebase.worker.adapter.OnAdapterItemsClickListener;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;
import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 2/20/18.
 */

public class HomeActivity extends BaseActivity implements HomeView.ViewImpl, View.OnClickListener {
    private HomeWorker worker;
    private HomeView view;
    private ArrayList<UserEntity> userEntities = new ArrayList<>();
    private LinearLayoutManager lm;
    private UserAdapter adapter;
    private GeneralFooterEntity footerEntity;

    @Override
    public BaseViewModel getViewModel() {
        return view;
    }

    @Override
    public BaseActivityControl getWorker() {
        return worker;
    }

    @Override
    public void onActivityCreate(Bundle bundle) {
        view = new HomeView(this, this, worker);
        worker = new HomeWorker(view);

        view.btnRight.setOnClickListener(this);

        footerEntity = new GeneralFooterEntity();
        lm = new LinearLayoutManager(thisActivity());
        view.rV.setLayoutManager(lm);
        view.rV.setNestedScrollingEnabled(false);
        view.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userEntities.clear();
                footerEntity.reset();
                worker.reqSearch(view.eSearch.getText().toString(), footerEntity.getCurrentPage() + 1);
            }
        });

        view.nestedScrollView.setOnScrollChangeListener(new NestedScrollViewListener() {
            @Override
            public void onScrolling(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {

            }

            @Override
            public void onStopScrolling(int i, int i1) {

            }

            @Override
            public void onLastChildLoaded(int scrollY) {
                Log.d("Load more onLastChildLoaded");
                if (footerEntity.isHasnNextPage()) {
                    worker.reqSearch(view.eSearch.getText().toString(), footerEntity.getCurrentPage() + 1);
                }
            }

            @Override
            public void onTopChildLoaded(int i) {

            }

            @Override
            public void onScrollingDown(int i, int i1) {

            }

            @Override
            public void onScrollingUp(int i, int i1) {

            }

        });
    }

    @Override
    protected void onBack() {
        finishWithAnim(ANIM_TYPE.LEFT_TO_RIGHT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRight:
                LoadingUtils.getInstance().show(getSupportFragmentManager());
                userEntities.clear();
                footerEntity.reset();
                worker.reqSearch(view.eSearch.getText().toString(), footerEntity.getCurrentPage() + 1);
                break;
        }
    }

    @Override
    public void resSearch(boolean y, String info, ArrayList<UserEntity> userEntities, int page, int total) {
        LoadingUtils.getInstance().dissmiss();
        if (y) {
            this.userEntities.addAll(userEntities);

            view.swipeRefreshLayout.setEnabled(true);
            view.swipeRefreshLayout.setRefreshing(false);

            footerEntity.setCurrentPage(page);
            footerEntity.setTotalData(total);

            setupAdapter();
        } else {
            showToast(info, R.layout.inflate_toast, R.id.tMsg);
        }
    }

    private void setupAdapter() {
        if (adapter == null) {
            adapter = new UserAdapter(thisActivity(), this.userEntities, null, footerEntity);
            view.rV.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnAdapterItemsClickListener() {
                @Override
                public void onItemsClicked(int viewType, int position, View v) {
                    UserEntity entity = (UserEntity) adapter.getContentByPosition(position);
                    switch (v.getId()) {
                        case R.id.root:
                            //showToast(entity.getLogin(), R.layout.inflate_toast, R.id.tMsg);
                            Bundle b = new Bundle();
                            b.putParcelable("UserEntity", entity);
                            redirect(UserActivity.class, b, ANIM_TYPE.RIGHT_TO_LEFT);
                            break;
                    }
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

        view.tLabel.setText(this.userEntities.size() + " of " + footerEntity.getTotalData());
    }

}
