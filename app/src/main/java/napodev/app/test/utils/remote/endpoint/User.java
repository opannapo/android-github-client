package napodev.app.test.utils.remote.endpoint;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by opannapo on 2/20/18.
 */

public interface User {
    @Headers(EndPoint.HeaderAccept)
    @GET("users/{username}")
    Call<ResponseBody> username(@Path("username") String username);
}
