package napodev.app.test.views.fragments.contributor;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.adapter.users.UserAdapter;
import napodev.app.test.entities.RepositoryEntity;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.LoadingUtils;
import napodev.app.test.views.activities.user.UserActivity;
import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.adapter.OnAdapterItemsClickListener;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class FragmentContributor extends BaseFragment implements FragmentContributorView.ViewImpl {
    FragmentContributorView view;
    FragmentContributorWorker worker;
    RepositoryEntity repositoryEntity;
    UserEntity userEntity;

    private ArrayList<UserEntity> entities = new ArrayList<>();
    private LinearLayoutManager lm;
    private UserAdapter adapter;

    @Override
    public BaseFragmentViewModel getViewModel() {
        return view;
    }

    @Override
    public BaseFragmentControl getWorker() {
        return worker;
    }

    @Override
    public void onViewCreatedBase(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new FragmentContributorView(this, this, worker, inflater, container);
        worker = new FragmentContributorWorker(this);

        repositoryEntity = getArguments().getParcelable("RepositoryEntity");
        userEntity = getArguments().getParcelable("UserEntity");

        lm = new LinearLayoutManager(getActivity());
        view.rV.setLayoutManager(lm);
        view.rV.setNestedScrollingEnabled(false);
    }

    @Override
    public void onHiddenChangedBase(boolean isHidden) {
        if (!isHidden && isFirstVisible()) {
            LoadingUtils.getInstance().show(getActivity().getSupportFragmentManager());
            worker.reqRepoContent(userEntity.getLogin(), repositoryEntity.getName());
        }
    }

    @Override
    public void onDestroyViewBase() {

    }

    @Override
    public void onAttachBase() {

    }

    @Override
    public void resRepoContent(boolean y, String info, ArrayList<UserEntity> entities) {
        LoadingUtils.getInstance().dissmiss();
        if (y) {
            this.entities.addAll(entities);
            if (adapter == null) {
                adapter = new UserAdapter(getActivity(), this.entities, null, null);
                view.rV.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnAdapterItemsClickListener() {
                    @Override
                    public void onItemsClicked(int viewType, int position, View v) {
                        UserEntity entity = (UserEntity) adapter.getContentByPosition(position);
                        switch (v.getId()) {
                            case R.id.root:
                                Bundle b = new Bundle();
                                b.putParcelable("UserEntity", entity);
                                ((BaseActivity) getActivity()).redirect(UserActivity.class, b, BaseActivity.ANIM_TYPE.RIGHT_TO_LEFT);
                                break;
                        }
                    }
                });
            } else {
                adapter.notifyDataSetChanged();
            }
        } else {
            ((BaseActivity) getActivity()).showToast(info, R.layout.inflate_toast, R.id.tMsg);
        }
    }
}
