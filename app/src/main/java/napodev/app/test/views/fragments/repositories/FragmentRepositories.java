package napodev.app.test.views.fragments.repositories;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.adapter.repositories.RepoAdapter;
import napodev.app.test.entities.RepositoryEntity;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.LoadingUtils;
import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.adapter.OnAdapterItemsClickListener;
import napodev.framework.bework.corebase.worker.view.BaseFragmentControl;

/**
 * Created by opannapo on 2/20/18.
 */

public class FragmentRepositories extends BaseFragment implements FragmentRepositoriesView.ViewImpl {
    FragmentRepositoriesView view;
    FragmentRepositoriesWorker worker;
    UserEntity userEntity;

    private ArrayList<RepositoryEntity> entities = new ArrayList<>();
    private LinearLayoutManager lm;
    private RepoAdapter adapter;

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
        view = new FragmentRepositoriesView(this, this, worker, inflater, container);
        worker = new FragmentRepositoriesWorker(this);

        userEntity = getArguments().getParcelable("UserEntity");
        lm = new LinearLayoutManager(getActivity());
        view.rV.setLayoutManager(lm);
        view.rV.setNestedScrollingEnabled(false);
    }

    @Override
    public void onHiddenChangedBase(boolean isHidden) {
        if (!isHidden && isFirstVisible()) {
            LoadingUtils.getInstance().show(getActivity().getSupportFragmentManager());
            worker.reqUserRepositories(userEntity.getLogin());
        }
    }

    @Override
    public void onDestroyViewBase() {

    }

    @Override
    public void onAttachBase() {

    }

    @Override
    public void resUserRepositories(boolean y, String info, ArrayList<RepositoryEntity> entities) {
        LoadingUtils.getInstance().dissmiss();
        if (y) {
            this.entities.addAll(entities);
            if (adapter == null) {
                adapter = new RepoAdapter(getActivity(), this.entities, null, null);
                view.rV.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnAdapterItemsClickListener() {
                    @Override
                    public void onItemsClicked(int viewType, int position, View v) {
                        RepositoryEntity entity = (RepositoryEntity) adapter.getContentByPosition(position);
                        switch (v.getId()) {
                            case R.id.root:
                                //showToast(entity.getLogin(), R.layout.inflate_toast, R.id.tMsg);

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
