package napodev.app.test.views.activities.repo;

import android.support.v4.widget.NestedScrollView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import napodev.app.test.R;
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


/**
 * Created by opannapo on 2/20/18.
 */

@Root(R.layout.activity_repo)
@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class RepoView extends BaseViewModel {
    @Child(R.id.btnLeft) ImageView btnLeft;
    @Child(R.id.tTitle) TextView tTitle;
    @Child(R.id.tName) public TextView tName;
    @Child(R.id.tUpdated) public TextView tUpdated;
    @Child(R.id.tDesc) public TextView tDesc;
    @Child(R.id.tForkCount) public TextView tForkCount;
    @Child(R.id.tWatchCount) public TextView tWatchCount;
    @Child(R.id.tLang) public TextView tLang;
    @Child(R.id.tLicenses) public TextView tLicenses;
    @Child(R.id.lay2) LinearLayout lay2;
    @Child(R.id.tContent) public TextView tContent;
    @Child(R.id.tContributors) public TextView tContributors;
    @Child(R.id.tBranches) public TextView tBranches;
    @Child(R.id.frameFragments) FrameLayout frameFragments;
    @Child(R.id.nestedScrollView) NestedScrollView nestedScrollView;


    public interface ViewImpl extends BaseViewImpl {
    }

    public interface WorkerImpl extends BaseWorkerImpl {
    }

    public RepoView(BaseActivity activity, BaseViewImpl viewImpl, WorkerImpl workerImpl) {
        super(activity, viewImpl, workerImpl);
    }
}
