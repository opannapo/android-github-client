package napodev.app.test.utils.remote;

import android.annotation.SuppressLint;
import android.os.Parcel;

import org.json.JSONArray;
import org.json.JSONObject;

import napodev.framework.bework.corebase.model.parcel.BaseParcelable;
import napodev.framework.bework.corebase.model.parcel.Entity;
import napodev.framework.bework.corebase.model.parcel.ParcelInject;
import napodev.framework.bework.utils.helper.JSONHelper;

/**
 * Created by opannapo on 2/20/18.
 */
@SuppressLint("ParcelCreator")
public class BaseResponseEntity extends BaseParcelable {
    @Entity(ParcelInject.BOOLEAN) private boolean success;
    @Entity(ParcelInject.JSONOBJECT) private JSONObject dataObj;
    @Entity(ParcelInject.JSONARRAY) private JSONArray dataArr;
    @Entity(ParcelInject.STRING) private String error;

    public BaseResponseEntity() {
    }

    public BaseResponseEntity(Parcel in) {
        super(in);
    }

    @Override
    public BaseParcelable parse(Object object) {
        dataObj = new JSONObject();
        dataArr = new JSONArray();
        if (object instanceof JSONObject) {
            if (((JSONObject) object).has("errors")) {
                success = false;
                error = JSONHelper.getString((JSONObject) object, "message");
            } else {
                success = true;
                dataObj = (JSONObject) object;
            }
        } else if (object instanceof JSONArray) {
            success = true;
            dataArr = (JSONArray) object;
        }

        return this;
    }

    public static final Creator<BaseResponseEntity> CREATOR = new Creator<BaseResponseEntity>() {
        @Override
        public BaseResponseEntity createFromParcel(Parcel in) {
            return new BaseResponseEntity(in);
        }

        @Override
        public BaseResponseEntity[] newArray(int size) {
            return new BaseResponseEntity[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JSONObject getDataObj() {
        return dataObj;
    }

    public void setDataObj(JSONObject dataObj) {
        this.dataObj = dataObj;
    }

    public JSONArray getDataArr() {
        return dataArr;
    }

    public void setDataArr(JSONArray dataArr) {
        this.dataArr = dataArr;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
