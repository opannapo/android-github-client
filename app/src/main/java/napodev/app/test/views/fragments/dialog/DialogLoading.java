package napodev.app.test.views.fragments.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import napodev.app.test.utils.Constant;
import napodev.app.test.R;
import napodev.framework.bework.corebase.view.dialogfragment.BaseDialogFragment;
import napodev.framework.bework.utils.napoinject.FontBold;
import napodev.framework.bework.utils.napoinject.FontDefault;
import napodev.framework.bework.utils.napoinject.FontItalic;

/**
 * Created by opannapo on 2/20/18.
 */

@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class DialogLoading extends BaseDialogFragment {

    @Override
    protected int layoutId() {
        return R.layout.inflate_dialog_loading;
    }

    @Override
    protected int[] layoutSize() {
        return new int[]{
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        };
    }

    @Override
    protected ColorDrawable layoutColorDrawable() {
        return new ColorDrawable(Color.TRANSPARENT);
    }

    @Override
    protected void onViewCreated(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
