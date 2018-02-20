package napodev.app.test.utils.remote;

import retrofit2.Retrofit;

/**
 * Created by opannapo on 2/20/18.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .build();
        }
        return retrofit;
    }
}
