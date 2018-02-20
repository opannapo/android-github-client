package napodev.app.test.views.activities.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import napodev.app.test.R;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.LoadingUtils;
import napodev.app.test.views.fragments.followers.FragmentFollowers;
import napodev.app.test.views.fragments.following.FragmentFollowing;
import napodev.app.test.views.fragments.repositories.FragmentRepositories;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;
import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 2/20/18.
 */

public class UserActivity extends BaseActivity implements UserView.ViewImpl, View.OnClickListener {
    private UserWorker worker;
    private UserView view;
    private UserEntity userEntity;

    private int selected_tab = -1;
    private final int TAB_REPO = 0;
    private final int TAB_FOLLOWING = 1;
    private final int TAB_FOLLOWERS = 2;

    private FragmentTransaction fragmentTransaction;
    private BaseFragment fragmentSelected;
    private FragmentRepositories fragmentRepositories;
    private FragmentFollowers fragmentFollowers;
    private FragmentFollowing fragmentFollowing;

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
        view = new UserView(this, this, worker);
        worker = new UserWorker(view);

        view.btnLeft.setOnClickListener(this);

        if (getBundleEtras() == null) return;

        userEntity = getBundleEtras().getParcelable("UserEntity");
        setupFragmentChilds();
        setupUserDetail();
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
            case R.id.tRepo:
                setupTab(TAB_REPO);
                break;
            case R.id.tFollowing:
                setupTab(TAB_FOLLOWING);
                break;
            case R.id.tFollowers:
                setupTab(TAB_FOLLOWERS);
                break;
        }
    }

    private void setupUserDetail() {
        view.tTitle.setText(userEntity.getLogin());
        view.tRepo.setOnClickListener(this);
        view.tFollowers.setOnClickListener(this);
        view.tFollowing.setOnClickListener(this);

        LoadingUtils.getInstance().show(getSupportFragmentManager());
        worker.reqUser(userEntity.getLogin());
    }

    @Override
    public void resUser(boolean y, String info, UserEntity userEntity) {
        LoadingUtils.getInstance().dissmiss();
        if (y) {
            this.userEntity = userEntity;

            Glide.with(this).load(userEntity.getAvatar_url()).into(view.imgProfile);
            view.tName.setText(userEntity.getName());
            view.tLocation.setText(TextUtils.isEmpty(userEntity.getLocation()) ? "Unknown" : userEntity.getLocation());
            view.tBio.setText("Bio - " + userEntity.getBio());
            view.tRepo.setText("Repositories " + String.valueOf(userEntity.getPublic_repos()));
            view.tFollowing.setText("Following " + String.valueOf(userEntity.getFollowing()));
            view.tFollowers.setText("Followers " + String.valueOf(userEntity.getFollowers()));
        } else {
            showToast(info, R.layout.inflate_toast, R.id.tMsg);
        }
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
        if (idx == TAB_REPO) {
            Log.d("TAB_REPO");
            fragmentSelected = fragmentRepositories;
            fragmentTransaction.show(fragmentRepositories);
            fragmentTransaction.hide(fragmentFollowers);
            fragmentTransaction.hide(fragmentFollowing);
        } else if (idx == TAB_FOLLOWERS) {
            Log.d("TAB_FOLLOWERS");
            fragmentSelected = fragmentFollowers;
            fragmentTransaction.hide(fragmentRepositories);
            fragmentTransaction.show(fragmentFollowers);
            fragmentTransaction.hide(fragmentFollowing);
        } else {
            Log.d("TAB_FOLLOWING");
            fragmentSelected = fragmentFollowing;
            fragmentTransaction.hide(fragmentRepositories);
            fragmentTransaction.hide(fragmentFollowers);
            fragmentTransaction.show(fragmentFollowing);
        }
        fragmentTransaction.commit();
    }

    public void setupFragmentChilds() {
        Bundle arg = new Bundle();
        arg.putParcelable("UserEntity", userEntity);

        fragmentRepositories = new FragmentRepositories();
        fragmentFollowers = new FragmentFollowers();
        fragmentFollowing = new FragmentFollowing();

        fragmentRepositories.setArguments(arg);
        fragmentFollowers.setArguments(arg);
        fragmentFollowing.setArguments(arg);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(view.frameFragments.getId(), fragmentRepositories);
        fragmentTransaction.add(view.frameFragments.getId(), fragmentFollowers);
        fragmentTransaction.add(view.frameFragments.getId(), fragmentFollowing);
        fragmentTransaction.commit();

        setupTab(TAB_REPO);
    }


}
