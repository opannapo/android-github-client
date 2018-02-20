package napodev.app.test.utils.remote;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import napodev.framework.bework.utils.Log;
import napodev.framework.bework.utils.helper.StringHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by opannapo on 2/20/18.
 */

public class API {
    ApiCallback apiCallback;
    Builder builder;
    Call<ResponseBody> call;
    String executedPath;
    String executedMethod;
    String reqInfo;
    BaseResponseEntity baseResponseEntity;

    public API() {

    }

    public Builder init(Call<ResponseBody> call) {
        this.call = call;
        this.builder = new Builder();
        executedMethod = call.request().method();
        executedPath = call.request().url().toString();
        reqInfo = executedMethod + "|" + executedPath;
        return this.builder;
    }


    public class Builder {
        public Builder executeCallback(ApiCallback callback) {
            apiCallback = callback;
            return this;
        }

        public void exe() {
            Log.d("Prepare " + call.request().method() + " " + call.request().url());
            runExecute();
        }

        private void runExecute() {
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    /*** ERROR REQUEST, CEK, BARANGKALI ADA RESPONSE FORMAT JSONS ***/
                    if (!response.isSuccessful()) {
                        try {
                            String strResult = response.errorBody().string();
                            String retrofitGeneralErrorMsg = response.message();

                            if (isJSONValid(strResult)) {
                                Log.e("onResponse errorBody IS JSON " + strResult);
                                JSONObject res = null;
                                try {
                                    res = new JSONObject(strResult);
                                    baseResponseEntity = new BaseResponseEntity();
                                    apiCallback.onResponseBeworkParcel(reqInfo, (BaseResponseEntity) baseResponseEntity.parse(res));
                                    apiCallback.onResponseJson(reqInfo, res);
                                    apiCallback.onResponseHttpBody(reqInfo, strResult);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.e("onResponse errorBody NOT JSON " + retrofitGeneralErrorMsg);
                                apiCallback.onResponseError(reqInfo, retrofitGeneralErrorMsg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            apiCallback.onResponseError(reqInfo, e.getMessage());
                        }

                        return;
                    }


                    /*** REQUEST SUKSES ***/
                    try {
                        String strResult = response.body().string();
                        Log.d("success " + reqInfo + " :: " + strResult);

                        if (StringHelper.isValidString(strResult)) {
                            if (isJSONValid(strResult)) {
                                JSONObject res = new JSONObject(strResult);
                                baseResponseEntity = new BaseResponseEntity();
                                apiCallback.onResponseBeworkParcel(reqInfo, (BaseResponseEntity) baseResponseEntity.parse(res));
                                apiCallback.onResponseJson(reqInfo, res);
                                apiCallback.onResponseHttpBody(reqInfo, strResult);
                            } else {
                                apiCallback.onResponseError(reqInfo, strResult);
                                Log.e("onResponse executedPath " + reqInfo + " " + strResult);
                            }
                        } else {
                            apiCallback.onResponseError(reqInfo, strResult);
                            Log.e("onResponse executedPath " + reqInfo + " " + strResult);
                        }
                    } catch (UnsupportedEncodingException e) {
                        apiCallback.onResponseError(reqInfo, e.getMessage());
                        e.printStackTrace();
                        Log.e(reqInfo + " onResponse UnsupportedEncodingException " + e.toString());

                    } catch (Exception e) {
                        apiCallback.onResponseError(reqInfo, e.getMessage());
                        Log.e(reqInfo + "  onResponse Exception " + e.toString());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    apiCallback.onResponseError(reqInfo, t.getMessage());
                    t.printStackTrace();
                    Log.e(reqInfo + "  onFailure throwable " + t.toString());
                }
            });
        }

        /**
         * Is json valid boolean.
         *
         * @param value the value
         * @return the boolean
         */
        private boolean isJSONValid(String value) {
            try {
                JsonElement jsonElem = new JsonParser().parse(value);
                if (jsonElem.isJsonObject()) {
                    return true;
                } else {
                    return false;
                }
            } catch (JsonSyntaxException e) {
                return false;
            } catch (Exception e) {
                return false;
            }
        }
    }
}
