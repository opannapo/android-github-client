package napodev.app.test.views.activities.user;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import napodev.app.test.R;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.LoadingUtils;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.view.BaseActivityControl;

public class UserActivity extends BaseActivity implements UserView.ViewImpl, View.OnClickListener {
    UserWorker worker;
    UserView view;
    UserEntity userEntity;

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
        }
    }

    private void setupUserDetail() {
        view.tTitle.setText(userEntity.getLogin());
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
            view.tLocation.setText(userEntity.getLocation());
            view.tBio.setText(userEntity.getBio());
        } else {
            showToast(info, R.layout.inflate_toast, R.id.tMsg);
        }
    }
}
