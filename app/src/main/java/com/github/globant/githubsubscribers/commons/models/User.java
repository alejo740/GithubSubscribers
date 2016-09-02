package com.github.globant.githubsubscribers.commons.models;

import com.google.gson.annotations.SerializedName;

/**
 * User class that represents each Github subscriber user information
 *
 * @author juan.herrera
 * @since 30/08/2016
 */
public class User {
    String login;
    int id;
    @SerializedName("avatar_url")
    String avatarUrl;
    @SerializedName("html_url")
    String htmlUrl;
    String name;
    String company;
    String location;
    @SerializedName("public_repos")
    int publicRepos;
    int followers;
    int following;

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
}