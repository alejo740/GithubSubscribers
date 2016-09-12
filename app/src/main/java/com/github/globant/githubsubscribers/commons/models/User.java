package com.github.globant.githubsubscribers.commons.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * User class that represents each Github subscriber user information
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class User implements Parcelable {
    private String login;
    private int id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    private String name;
    private String company;
    private String location;
    @SerializedName("public_repos")
    private int publicRepos;
    private int followers;
    private int following;

    public User(String login, int id, String avatarUrl, String htmlUrl, String name,
                String company, String location, int publicRepos, int followers, int following) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.name = name;
        this.company = company;
        this.location = location;
        this.publicRepos = publicRepos;
        this.followers = followers;
        this.following = following;
    }

    protected User(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatarUrl = in.readString();
        htmlUrl = in.readString();
        name = in.readString();
        company = in.readString();
        location = in.readString();
        publicRepos = in.readInt();
        followers = in.readInt();
        following = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(id);
        dest.writeString(avatarUrl);
        dest.writeString(htmlUrl);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(location);
        dest.writeInt(publicRepos);
        dest.writeInt(followers);
        dest.writeInt(following);
    }
}
