package napodev.app.test.adapter.repositories;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import napodev.app.test.R;
import napodev.app.test.entities.RepositoryEntity;
import napodev.app.test.utils.Constant;
import napodev.app.test.utils.DateHelper;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.adapter.BaseRecyclerViewAdapter;
import napodev.framework.bework.corebase.worker.adapter.BaseViewHolder;
import napodev.framework.bework.utils.helper.JSONHelper;
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
    @Child(R.id.root) public RelativeLayout root;
    @Child(R.id.tName) public TextView tName;
    @Child(R.id.tUpdated) public TextView tUpdated;
    @Child(R.id.tDesc) public TextView tDesc;
    @Child(R.id.tForkCount) public TextView tForkCount;
    @Child(R.id.tWatchCount) public TextView tWatchCount;
    @Child(R.id.tLang) public TextView tLang;
    @Child(R.id.tLicenses) public TextView tLicenses;

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
        RepositoryEntity entity = (RepositoryEntity) objectEntities;
        tName.setText(entity.getName());
        tUpdated.setText("Updated on " + DateHelper.formatToMyZone(entity.getUpdated_at()));
        tDesc.setText(entity.getDescription());


        tForkCount.setText(String.valueOf(entity.getForks_count()));
        tWatchCount.setText(String.valueOf(entity.getWatchers_count()));
        tLang.setText(String.valueOf(entity.getLanguage()));
        tLicenses.setText(String.valueOf(JSONHelper.getString(entity.getLicense(), "spdx_id")));

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().getOnItemClickListener().onItemsClicked(1, position, root);
            }
        });
    }
}