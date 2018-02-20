package napodev.app.test.utils.remote.endpoint;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by opannapo on 2/20/18.
 */

public interface Search {
    @Headers(EndPoint.HeaderAccept)
    @GET("search/users")
    Call<ResponseBody> users(@QueryMap Map<String, String> params);
}
