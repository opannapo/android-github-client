package napodev.app.test.utils.remote.endpoint;


import napodev.app.test.utils.remote.RetrofitClient;

/**
 * Created by opannapo on 2/20/18.
 */

public class EndPoint {
    private EndPoint() {
    }

    public static final String BASE_URL = "https://api.github.com";
    public static final String HeaderAccept = "Accept:application/vnd.github.v3+json";

    public static Search search() {
        return RetrofitClient.getClient(BASE_URL).create(Search.class);
    }

    public static User user() {
        return RetrofitClient.getClient(BASE_URL).create(User.class);
    }


}
