package napodev.app.test.utils.remote;

import java.util.HashMap;

import napodev.framework.bework.utils.Log;

/**
 * Created by opannapo on 2/20/18.
 */

public class GetFields extends HashMap<String, String> {
    public void print() {
        for (String key : keySet()) {
            Log.d(key + " :: " + get(key));
        }
    }
}
