package napodev.app.test.views.fragments.contributor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import napodev.app.test.R;
import napodev.app.test.entities.UserEntity;
import napodev.app.test.utils.Constant;
import napodev.framework.bework.corebase.model.view.BaseFragmentViewImpl;
import napodev.framework.bework.corebase.model.view.BaseFragmentViewModel;
import napodev.framework.bework.corebase.model.view.BaseFragmentWorkerImpl;
import napodev.framework.bework.corebase.view.BaseFragment;
import napodev.framework.bework.utils.napoinject.Child;
import napodev.framework.bework.utils.napoinject.FontBold;
import napodev.framework.bework.utils.napoinject.FontDefault;
import napodev.framework.bework.utils.napoinject.FontItalic;
import napodev.framework.bework.utils.napoinject.Root;

/**
 * Created by opannapo on 2/20/18.
 */

@Root(R.layout.fragment_list)
@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class FragmentContributorView extends BaseFragmentViewModel {
    @Child(R.id.rV) RecyclerView rV;

    public interface ViewImpl extends BaseFragmentViewImpl {
        void resRepoContent(boolean y, String info, ArrayList<UserEntity> entities);
    }

    public interface WorkerImpl extends BaseFragmentWorkerImpl {
        void reqRepoContent(String username, String reponame);
    }

    public FragmentContributorView(BaseFragment fragment, BaseFragmentViewImpl viewImpl, BaseFragmentWorkerImpl workerImpl, LayoutInflater inflater, ViewGroup container) {
        super(fragment, viewImpl, workerImpl, inflater, container);
    }
}
