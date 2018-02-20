package napodev.app.test.adapter.users;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import napodev.app.test.R;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.Constant;
import napodev.app.test.utils.DecimalHelper;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.adapter.BaseRecyclerViewAdapter;
import napodev.framework.bework.corebase.worker.adapter.BaseViewHolder;
import napodev.framework.bework.utils.napoinject.Child;
import napodev.framework.bework.utils.napoinject.FontBold;
import napodev.framework.bework.utils.napoinject.FontDefault;
import napodev.framework.bework.utils.napoinject.FontInjector;
import napodev.framework.bework.utils.napoinject.FontItalic;
import napodev.framework.bework.utils.napoinject.ViewInjector;

/**
 * Created by opannapo on 2/20/18.
 */
@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class VHContent extends BaseViewHolder {
    @Child(R.id.tName) public TextView tName;
    @Child(R.id.tScore) public TextView tScore;
    @Child(R.id.imgProfile) public CircleImageView imgProfile;
    @Child(R.id.root) public RelativeLayout root;


    public VHContent(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    public void onInit(View itemView) {
        ViewInjector.inject(this, (ViewGroup) itemView);
        FontInjector.inject(this, (BaseActivity) getContext(), (ViewGroup) itemView);
    }

    @Override
    public void onViewBinding(Object objectEntities, final int position) {
        UserEntity entity = (UserEntity) objectEntities;
        Glide.with(getContext()).load(entity.getAvatar_url()).into(imgProfile);
        tName.setText(entity.getLogin());
        tScore.setText("Score " + String.valueOf(DecimalHelper.format(entity.getScore())));
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().getOnItemClickListener().onItemsClicked(1, position, root);
            }
        });
    }
}