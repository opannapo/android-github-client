package napodev.app.test.utils.remote;

/**
 * Created by opannapo on 2/20/18.
 */

public abstract class ApiCallback {
    public void onResponseHttpBody(String url, String response) {

    }

    public void onResponseJson(String url, Object response) {

    }

    public void onResponseBeworkParcel(String url, BaseResponseEntity responseEntity) {

    }

    public void onResponseError(String url, String info) {

    }
}
