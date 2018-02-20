package napodev.app.test.views.activities.repo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import napodev.app.test.R;
import napodev.app.test.entities.RepositoryEntity;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.DateHelper;
import napodev.app.test.views.fragments.branch.FragmentBranch;
import napodev.app.test.views.fragments.content.FragmentContent;
import napodev.app.test.views.fragments.contributor.FragmentContributor;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;
import napodev.framework.bework.utils.helper.JSONHelper;

/**
 * Created by opannapo on 2/20/18.
 */

public class RepoActivity extends BaseActivity implements RepoView.ViewImpl, View.OnClickListener {
    private RepoWorker worker;
    private RepoView view;
    private RepositoryEntity repositoryEntity;
    private UserEntity userEntity;
    private int selected_tab = -1;
    private final int TAB_CONTENT = 0;
    private final int TAB_CONTRIBUTORS = 1;
    private final int TAB_BRANCH = 2;
    private FragmentTransaction fragmentTransaction;
    private FragmentContent fragmentContent;
    private FragmentContributor fragmentContributor;
    private FragmentBranch fragmentBranch;

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
        view = new RepoView(this, this, worker);
        worker = new RepoWorker(view);

        view.btnLeft.setOnClickListener(this);

        if (getBundleEtras() == null) return;

        repositoryEntity = getBundleEtras().getParcelable("RepositoryEntity");
        userEntity = getBundleEtras().getParcelable("UserEntity");

        setupBaseData();
    }

    @Override
    protected void onBack() {
        finishWithAnim(ANIM_TYPE.LEFT_TO_RIGHT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLeft:
                onBack();
                break;
            case R.id.tContent:
                setupTab(TAB_CONTENT);
                break;
            case R.id.tContributors:
                setupTab(TAB_CONTRIBUTORS);
                break;
            case R.id.tBranches:
                setupTab(TAB_BRANCH);
                break;
        }
    }

    private void setupBaseData() {
        view.tTitle.setText(repositoryEntity.getName());
        view.tName.setText(repositoryEntity.getName());
        view.tUpdated.setText("Updated on " + DateHelper.formatToMyZone(repositoryEntity.getUpdated_at()));
        view.tDesc.setText(repositoryEntity.getDescription());
        view.tForkCount.setText(String.valueOf(repositoryEntity.getForks_count()));
        view.tWatchCount.setText(String.valueOf(repositoryEntity.getWatchers_count()));
        view.tLang.setText(String.valueOf(repositoryEntity.getLanguage()));
        view.tLicenses.setText(String.valueOf(JSONHelper.getString(repositoryEntity.getLicense(), "spdx_id")));

        view.tContent.setOnClickListener(this);
        view.tContributors.setOnClickListener(this);
        view.tBranches.setOnClickListener(this);

        setupFragmentChilds();
    }

    private void setupTab(int idx) {
        if (selected_tab == idx) return;
        view.nestedScrollView.smoothScrollTo(0, 0);

        for (int i = 0; i < view.lay2.getChildCount(); i++) {
            TextView t = (TextView) view.lay2.getChildAt(i);
            if (idx == i) {
                t.setAlpha(1.0f);
                t.setTextColor(Color.parseColor("#31a3a3"));
                selected_tab = idx;
            } else {
                t.setAlpha(0.3f);
                t.setTextColor(Color.parseColor("#000000"));
            }
        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (idx == TAB_CONTENT) {
            fragmentTransaction.show(fragmentContent);
            fragmentTransaction.hide(fragmentContributor);
            fragmentTransaction.hide(fragmentBranch);
        } else if (idx == TAB_CONTRIBUTORS) {
            fragmentTransaction.hide(fragmentContent);
            fragmentTransaction.show(fragmentContributor);
            fragmentTransaction.hide(fragmentBranch);
        } else {
            fragmentTransaction.hide(fragmentContent);
            fragmentTransaction.hide(fragmentContributor);
            fragmentTransaction.show(fragmentBranch);
        }
        fragmentTransaction.commit();
    }

    public void setupFragmentChilds() {
        Bundle arg = new Bundle();
        arg.putParcelable("RepositoryEntity", repositoryEntity);
        arg.putParcelable("UserEntity", userEntity);

        fragmentContent = new FragmentContent();
        fragmentContributor = new FragmentContributor();
        fragmentBranch = new FragmentBranch();

        fragmentContent.setArguments(arg);
        fragmentContributor.setArguments(arg);
        fragmentBranch.setArguments(arg);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(view.frameFragments.getId(), fragmentContent);
        fragmentTransaction.add(view.frameFragments.getId(), fragmentContributor);
        fragmentTransaction.add(view.frameFragments.getId(), fragmentBranch);
        fragmentTransaction.commit();

        setupTab(TAB_CONTENT);
    }

}
