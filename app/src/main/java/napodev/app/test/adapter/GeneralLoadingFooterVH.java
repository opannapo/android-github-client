package napodev.app.test.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import napodev.app.test.R;
import napodev.app.test.entities.GeneralFooterEntity;
import napodev.app.test.utils.Constant;
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
public class GeneralLoadingFooterVH extends BaseViewHolder {
    @Child(R.id.tLabel) TextView tLabel;
    @Child(R.id.progressBar) ProgressBar progressBar;

    public GeneralLoadingFooterVH(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    public void onInit(View itemView) {
        ViewInjector.inject(this, (ViewGroup) itemView);
        FontInjector.inject(this, (BaseActivity) getContext(), (ViewGroup) itemView);
    }

    @Override
    public void onViewBinding(Object objectEntities, int position) {
        GeneralFooterEntity entity = (GeneralFooterEntity) objectEntities;
        if (entity.isHasnNextPage()) {
            tLabel.setText("Loading");
            tLabel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            tLabel.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            tLabel.setText("No More");
        }
    }
}
