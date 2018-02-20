package napodev.app.test.views.fragments.branch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.adapter.content.ContentAdapter;
import napodev.app.test.entities.ContentEntity;
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

public class FragmentBranch extends BaseFragment implements FragmentBranchView.ViewImpl {
    FragmentBranchView view;
    FragmentBranchWorker worker;
    RepositoryEntity repositoryEntity;
    UserEntity userEntity;

    private ArrayList<ContentEntity> entities = new ArrayList<>();
    private LinearLayoutManager lm;
    private ContentAdapter adapter;

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
        view = new FragmentBranchView(this, this, worker, inflater, container);
        worker = new FragmentBranchWorker(this);

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
            worker.reqRepoBranch(userEntity.getLogin(), repositoryEntity.getName());
        }
    }

    @Override
    public void onDestroyViewBase() {

    }

    @Override
    public void onAttachBase() {

    }

    @Override
    public void resRepoBranch(boolean y, String info, ArrayList<ContentEntity> entities) {
        LoadingUtils.getInstance().dissmiss();
        if (y) {
            this.entities.addAll(entities);
            if (adapter == null) {
                adapter = new ContentAdapter(getActivity(), this.entities, null, null);
                view.rV.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnAdapterItemsClickListener() {
                    @Override
                    public void onItemsClicked(int viewType, int position, View v) {
                        switch (v.getId()) {
                            case R.id.root:
                                Uri webpage = Uri.parse(repositoryEntity.getHtml_url());
                                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                    startActivity(intent);
                                } else {
                                    ((BaseActivity) getActivity()).showToast("Can't open browser", R.layout.inflate_toast, R.id.tMsg);
                                }
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
