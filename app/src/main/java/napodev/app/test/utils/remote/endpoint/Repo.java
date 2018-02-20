package napodev.app.test.utils.remote.endpoint;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by opannapo on 2/20/18.
 */

public interface Repo {
    @Headers(EndPoint.HeaderAccept)
    @GET("repos/{username}/{reponame}/contents")
    Call<ResponseBody> contents(@Path("username") String username, @Path("reponame") String reponame);

    @Headers(EndPoint.HeaderAccept)
    @GET("repos/{username}/{reponame}/contributors")
    Call<ResponseBody> contributors(@Path("username") String username, @Path("reponame") String reponame);

    @Headers(EndPoint.HeaderAccept)
    @GET("repos/{username}/{reponame}/branches")
    Call<ResponseBody> branches(@Path("username") String username, @Path("reponame") String reponame);
}
