package napodev.app.test.views.activities.user;

import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
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


@Root(R.layout.activity_user)
@FontDefault(Constant.Font.DEFAULT)
@FontItalic(Constant.Font.ITALIC)
@FontBold(Constant.Font.BOLD)
public class UserView extends BaseViewModel {
    @Child(R.id.btnLeft) ImageView btnLeft;
    @Child(R.id.tTitle) TextView tTitle;
    @Child(R.id.tName) public TextView tName;
    @Child(R.id.tScore) public TextView tScore;
    @Child(R.id.imgProfile) public CircleImageView imgProfile;


    public interface ViewImpl extends BaseViewImpl {
        void resUser(boolean y, String info, UserEntity userEntity);
    }

    public interface WorkerImpl extends BaseWorkerImpl {
        void reqUser(String val);
    }

    public UserView(BaseActivity activity, BaseViewImpl viewImpl, WorkerImpl workerImpl) {
        super(activity, viewImpl, workerImpl);
    }
}