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
public class UserEntity extends BaseParcelable {
    @Entity(ParcelInject.STRING) private String login;
    @Entity(ParcelInject.INT) private int id;
    @Entity(ParcelInject.STRING) private String avatar_url;
    @Entity(ParcelInject.STRING) private String gravatar_id;
    @Entity(ParcelInject.STRING) private String url;
    @Entity(ParcelInject.STRING) private String html_url;
    @Entity(ParcelInject.STRING) private String followers_url;
    @Entity(ParcelInject.STRING) private String following_url;
    @Entity(ParcelInject.STRING) private String gists_url;
    @Entity(ParcelInject.STRING) private String starred_url;
    @Entity(ParcelInject.STRING) private String subscriptions_url;
    @Entity(ParcelInject.STRING) private String organizations_url;
    @Entity(ParcelInject.STRING) private String repos_url;
    @Entity(ParcelInject.STRING) private String events_url;
    @Entity(ParcelInject.STRING) private String received_events_url;
    @Entity(ParcelInject.STRING) private String type;
    @Entity(ParcelInject.BOOLEAN) private boolean site_admin;
    @Entity(ParcelInject.DOUBLE) private double score;

    @Entity(ParcelInject.STRING) private String name;
    @Entity(ParcelInject.STRING) private String company;
    @Entity(ParcelInject.STRING) private String blog;
    @Entity(ParcelInject.STRING) private String location;
    @Entity(ParcelInject.STRING) private String email;
    @Entity(ParcelInject.STRING) private String hireable;
    @Entity(ParcelInject.STRING) private String bio;
    @Entity(ParcelInject.INT) private int public_repos;
    @Entity(ParcelInject.INT) private int public_gists;
    @Entity(ParcelInject.INT) private int followers;
    @Entity(ParcelInject.INT) private int following;
    @Entity(ParcelInject.STRING) private String created_at;
    @Entity(ParcelInject.STRING) private String updated_at;

    public UserEntity() {
    }

    public UserEntity(Parcel in) {
        super(in);
    }

    @Override
    public BaseParcelable parse(Object object) {
        JSONObject o = (JSONObject) object;
        setLogin(JSONHelper.getString(o, "login"));
        setId(JSONHelper.getInt(o, "id"));
        setAvatar_url(JSONHelper.getString(o, "avatar_url"));
        setGravatar_id(JSONHelper.getString(o, "gravatar_id"));
        setUrl(JSONHelper.getString(o, "url"));
        setHtml_url(JSONHelper.getString(o, "html_url"));
        setFollowers_url(JSONHelper.getString(o, "followers_url"));
        setFollowing_url(JSONHelper.getString(o, "following_url"));
        setGists_url(JSONHelper.getString(o, "gists_url"));
        setStarred_url(JSONHelper.getString(o, "starred_url"));
        setSubscriptions_url(JSONHelper.getString(o, "subscriptions_url"));
        setOrganizations_url(JSONHelper.getString(o, "organizations_url"));
        setRepos_url(JSONHelper.getString(o, "repos_url"));
        setEvents_url(JSONHelper.getString(o, "events_url"));
        setReceived_events_url(JSONHelper.getString(o, "received_events_url"));
        setType(JSONHelper.getString(o, "type"));
        setSite_admin(JSONHelper.getBool(o, "site_admin"));
        setScore(JSONHelper.getDouble(o, "score"));
        setName(JSONHelper.getString(o, "name"));
        setCompany(JSONHelper.getString(o, "company"));
        setBlog(JSONHelper.getString(o, "blog"));
        setLocation(JSONHelper.getString(o, "location"));
        setEmail(JSONHelper.getString(o, "email"));
        setHireable(JSONHelper.getString(o, "hireable"));
        setBio(JSONHelper.getString(o, "bio"));
        setPublic_repos(JSONHelper.getInt(o, "public_repos"));
        setPublic_gists(JSONHelper.getInt(o, "public_gists"));
        setFollowers(JSONHelper.getInt(o, "followers"));
        setFollowing(JSONHelper.getInt(o, "following"));
        setCreated_at(JSONHelper.getString(o, "created_at"));
        setUpdated_at(JSONHelper.getString(o, "updated_at"));

        return this;
    }

    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
        @Override
        public UserEntity createFromParcel(Parcel in) {
            return new UserEntity(in);
        }

        @Override
        public UserEntity[] newArray(int size) {
            return new UserEntity[size];
        }
    };


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
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

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireable() {
        return hireable;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(int public_gists) {
        this.public_gists = public_gists;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
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
}

