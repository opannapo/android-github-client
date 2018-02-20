package napodev.app.test.entities;

import android.annotation.SuppressLint;
import android.os.Parcel;

import org.json.JSONObject;

import napodev.framework.bework.corebase.model.parcel.BaseParcelable;
import napodev.framework.bework.corebase.model.parcel.Entity;
import napodev.framework.bework.corebase.model.parcel.ParcelInject;
import napodev.framework.bework.utils.helper.JSONHelper;

/**
 * Created by opannapo on 2/20/18.
 */

@SuppressLint("ParcelCreator")
public class ContentEntity extends BaseParcelable {
    @Entity(ParcelInject.STRING) private String name;
    @Entity(ParcelInject.STRING) private String path;
    @Entity(ParcelInject.STRING) private String sha;
    @Entity(ParcelInject.INT) private int size;
    @Entity(ParcelInject.STRING) private String url;
    @Entity(ParcelInject.STRING) private String html_url;
    @Entity(ParcelInject.STRING) private String git_url;
    @Entity(ParcelInject.STRING) private String download_url;
    @Entity(ParcelInject.STRING) private String type;


    public ContentEntity() {
    }

    public ContentEntity(Parcel in) {
        super(in);
    }

    @Override
    public BaseParcelable parse(Object object) {
        JSONObject o = (JSONObject) object;
        setName(JSONHelper.getString(o, "name"));
        setPath(JSONHelper.getString(o, "path"));
        setSha(JSONHelper.getString(o, "sha"));
        setSize(JSONHelper.getInt(o, "size"));
        setUrl(JSONHelper.getString(o, "url"));
        setHtml_url(JSONHelper.getString(o, "html_url"));
        setGit_url(JSONHelper.getString(o, "git_url"));
        setDownload_url(JSONHelper.getString(o, "download_url"));
        setType(JSONHelper.getString(o, "type"));

        return this;
    }

    public static final Creator<ContentEntity> CREATOR = new Creator<ContentEntity>() {
        @Override
        public ContentEntity createFromParcel(Parcel in) {
            return new ContentEntity(in);
        }

        @Override
        public ContentEntity[] newArray(int size) {
            return new ContentEntity[size];
        }
    };


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

