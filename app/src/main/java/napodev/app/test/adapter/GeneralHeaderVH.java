package napodev.app.test.adapter;

import android.view.View;
import android.view.ViewGroup;

import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.corebase.worker.adapter.BaseRecyclerViewAdapter;
import napodev.framework.bework.corebase.worker.adapter.BaseViewHolder;
import napodev.framework.bework.utils.napoinject.FontBold;
import napodev.framework.bework.utils.napoinject.FontDefault;
import napodev.framework.bework.utils.napoinject.FontInjector;
import napodev.framework.bework.utils.napoinject.FontItalic;
import napodev.framework.bework.utils.napoinject.ViewInjector;

/**
 * Created by opannapo on 2/20/18.
 */

@FontDefault("Raleway-Regular.ttf")
@FontItalic("Infinity.ttf")
@FontBold("Raleway-Bold.ttf")
public class GeneralHeaderVH extends BaseViewHolder {
    /*@Child(R.id.tLabel) TextView tLabel;
    @Child(R.id.imgLogoType) ImageView imgLogoType;*/


    public GeneralHeaderVH(View itemView, BaseRecyclerViewAdapter adapter) {
        super(itemView, adapter);
    }

    @Override
    public void onInit(View itemView) {
        ViewInjector.inject(this, (ViewGroup) itemView);
        FontInjector.inject(this, (BaseActivity) getContext(), (ViewGroup) itemView);
    }

    @Override
    public void onViewBinding(Object objectEntities, int position) {
        /*GeneralHeaderDataModel headerDataModel = (GeneralHeaderDataModel) objectEntities;
        tLabel.setText(headerDataModel.getLabel());
        imgLogoType.setImageResource(headerDataModel.getIconRes());*/
    }

}
