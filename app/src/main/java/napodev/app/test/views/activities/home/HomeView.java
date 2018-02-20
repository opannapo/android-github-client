package napodev.app.test.views.activities.home;

import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.Constant;
import napodev.framework.bework.corebase.model.view.BaseViewImpl;
import napodev.framework.bework.corebase.model.view.BaseViewModel;
import napodev.framework.bework.corebase.model.view.BaseWorkerImpl;
import napodev.framework.bework.corebase.view.BaseActivity;
import napodev.framework.bework.utils.napoinject.Child;
import napodev.framework.bework.utils.napoinject.FontBold;
import napodev.framework.bework.utils.napoinject.FontDefault;
import napodev.framework.bework.utils.napoinject.FontItalic;
import napodev.framework.bework.utils.napoinject.Root;


@Root(R.layout.activity_main)
@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class HomeView extends BaseViewModel {
    @Child(R.id.eSearch) public EditText eSearch;
    @Child(R.id.btnRight) public ImageView btnRight;
    @Child(R.id.rV) RecyclerView rV;
    @Child(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Child(R.id.nestedScrollView) NestedScrollView nestedScrollView;
    @Child(R.id.tLabel) TextView tLabel;


    public interface ViewImpl extends BaseViewImpl {
        void resSearch(boolean y, String info, ArrayList<UserEntity> userEntities, int page, int total);
    }

    public interface WorkerImpl extends BaseWorkerImpl {
        void reqSearch(String val, int page);
    }

    public HomeView(BaseActivity activity, BaseViewImpl viewImpl, WorkerImpl workerImpl) {
        super(activity, viewImpl, workerImpl);
    }
}
