package napodev.app.test.adapter.content;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.framework.bework.corebase.worker.adapter.BaseRecyclerViewAdapter;
import napodev.framework.bework.corebase.worker.adapter.BaseViewHolder;

/**
 * Created by opannapo on 2/20/18.
 */

public class ContentAdapter extends BaseRecyclerViewAdapter {
    private static final int VIEW_TYPE_DEFAULT = 1;

    public ContentAdapter(Context context, ArrayList<?> dataModels, Object headerObject, Object footerObject) {
        super(context, (ArrayList<Object>) dataModels, headerObject, footerObject);
    }

    @Override
    public int contentItemsViewType(int position) {
        return VIEW_TYPE_DEFAULT;
    }

    @Override
    public RecyclerView.ViewHolder createItemsViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_content, parent, false);
            return new VHContent(v, this);
        } else {
            return null;
        }
    }

    @Override
    public BaseViewHolder createHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public BaseViewHolder createFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}

