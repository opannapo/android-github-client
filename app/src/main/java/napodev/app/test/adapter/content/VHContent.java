package napodev.app.test.adapter.content;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import napodev.app.test.R;
import napodev.app.test.entities.ContentEntity;
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
public class VHContent extends BaseViewHolder {
    @Child(R.id.root) public RelativeLayout root;
    @Child(R.id.tName) public TextView tName;
    @Child(R.id.img) public ImageView img;

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
        ContentEntity entity = (ContentEntity) objectEntities;
        tName.setText(entity.getName());
        if (entity.getType().equals("file")) {
            img.setImageResource(R.drawable.ic_attachment_black_24dp);
        } else if (entity.getType().equals("dir")) {
            img.setImageResource(R.drawable.ic_folder_black_24dp);
        } else {
            img.setImageResource(R.drawable.ic_code);
        }

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdapter().getOnItemClickListener().onItemsClicked(1, position, root);
            }
        });
    }
}