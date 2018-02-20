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
public class RepositoryEntity extends BaseParcelable {
    @Entity(ParcelInject.INT) private int id;
    @Entity(ParcelInject.STRING) private String name;
    @Entity(ParcelInject.STRING) private String full_name;
    @Entity(ParcelInject.STRING) private String html_url;
    @Entity(ParcelInject.STRING) private String description;
    @Entity(ParcelInject.BOOLEAN) private boolean fork;
    @Entity(ParcelInject.STRING) private String created_at;
    @Entity(ParcelInject.STRING) private String updated_at;
    @Entity(ParcelInject.STRING) private String pushed_at;
    @Entity(ParcelInject.STRING) private String git_url;
    @Entity(ParcelInject.STRING) private String ssh_url;
    @Entity(ParcelInject.STRING) private String clone_url;
    @Entity(ParcelInject.STRING) private String homepage;
    @Entity(ParcelInject.INT) private int size;
    @Entity(ParcelInject.INT) private int stargazers_count;
    @Entity(ParcelInject.INT) private int watchers_count;
    @Entity(ParcelInject.STRING) private String language;
    @Entity(ParcelInject.BOOLEAN) private boolean has_issues;
    @Entity(ParcelInject.BOOLEAN) private boolean has_projects;
    @Entity(ParcelInject.BOOLEAN) private boolean has_downloads;
    @Entity(ParcelInject.BOOLEAN) private boolean has_wiki;
    @Entity(ParcelInject.BOOLEAN) private boolean has_pages;
    @Entity(ParcelInject.INT) private int forks_count;
    @Entity(ParcelInject.STRING) private String mirror_url;
    @Entity(ParcelInject.BOOLEAN) private boolean archived;
    @Entity(ParcelInject.JSONOBJECT) private JSONObject license;
    @Entity(ParcelInject.INT) private int forks;
    @Entity(ParcelInject.INT) private int open_issues;
    @Entity(ParcelInject.INT) private int watchers;
    @Entity(ParcelInject.STRING) private String default_branch;
    @Entity(ParcelInject.PARCELABLE) private UserEntity owner;

    public RepositoryEntity() {
    }

    public RepositoryEntity(Parcel in) {
        super(in);
    }

    @Override
    public BaseParcelable parse(Object object) {
        JSONObject o = (JSONObject) object;

        setId(JSONHelper.getInt(o, "id"));
        setName(JSONHelper.getString(o, "name"));
        setFull_name(JSONHelper.getString(o, "full_name"));
        setHtml_url(JSONHelper.getString(o, "html_url"));
        setDescription(JSONHelper.getString(o, "description"));
        setFork(JSONHelper.getBool(o, "fork"));
        setCreated_at(JSONHelper.getString(o, "created_at"));
        setUpdated_at(JSONHelper.getString(o, "updated_at"));
        setPushed_at(JSONHelper.getString(o, "pushed_at"));
        setGit_url(JSONHelper.getString(o, "git_url"));
        setSsh_url(JSONHelper.getString(o, "ssh_url"));
        setClone_url(JSONHelper.getString(o, "clone_url"));
        setHomepage(JSONHelper.getString(o, "homepage"));
        setSize(JSONHelper.getInt(o, "size"));
        setStargazers_count(JSONHelper.getInt(o, "stargazers_count"));
        setWatchers_count(JSONHelper.getInt(o, "watchers_count"));
        setLanguage(JSONHelper.getString(o, "language"));
        setHas_issues(JSONHelper.getBool(o, "has_issues"));
        setHas_projects(JSONHelper.getBool(o, "has_projects"));
        setHas_downloads(JSONHelper.getBool(o, "has_downloads"));
        setHas_wiki(JSONHelper.getBool(o, "has_wiki"));
        setHas_pages(JSONHelper.getBool(o, "has_pages"));
        setForks_count(JSONHelper.getInt(o, "forks_count"));
        setMirror_url(JSONHelper.getString(o, "mirror_url"));
        setArchived(JSONHelper.getBool(o, "archived"));
        setForks(JSONHelper.getInt(o, "forks"));
        setOpen_issues(JSONHelper.getInt(o, "open_issues"));
        setWatchers(JSONHelper.getInt(o, "watchers"));
        setDefault_branch(JSONHelper.getString(o, "default_branch"));

        if (o.has("owner")) {
            setOwner((UserEntity) new UserEntity().parse(JSONHelper.getObject(o, "owner")));
        }

        if (o.has("license")) {
            setLicense(JSONHelper.getObject(o, "license"));
        }

        return this;
    }

    public static final Creator<RepositoryEntity> CREATOR = new Creator<RepositoryEntity>() {
        @Override
        public RepositoryEntity createFromParcel(Parcel in) {
            return new RepositoryEntity(in);
        }

        @Override
        public RepositoryEntity[] newArray(int size) {
            return new RepositoryEntity[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public String getGit_url() {
        return git_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public boolean isHas_projects() {
        return has_projects;
    }

    public void setHas_projects(boolean has_projects) {
        this.has_projects = has_projects;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getMirror_url() {
        return mirror_url;
    }

    public void setMirror_url(String mirror_url) {
        this.mirror_url = mirror_url;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public JSONObject getLicense() {
        return license;
    }

    public void setLicense(JSONObject license) {
        this.license = license;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}

