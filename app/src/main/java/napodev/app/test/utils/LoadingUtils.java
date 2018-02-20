package napodev.app.test.utils;

import android.support.v4.app.FragmentManager;

import napodev.app.test.views.fragments.dialog.DialogLoading;
import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 2/20/18.
 */

public class LoadingUtils {
    private DialogLoading loading;
    private static LoadingUtils INSTANCE;

    private LoadingUtils() {

    }

    public static LoadingUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingUtils();
        }

        return INSTANCE;
    }

    public void show(FragmentManager fm) {
        try {
            if (loading == null) {
                loading = new DialogLoading();
                loading.setSupportFragmentManager(fm);
                loading.setCancelable(false);
                loading.show();
            } else {
                Log.e("Already Showing !!!!");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dissmiss() {
        try {
            if (loading != null) {
                loading.dismiss();
            } else {
                Log.e("Not Created Before!!!!");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loading = null;
        INSTANCE = null;
    }
}
