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
    @SerializedName("gravatar_id")
    String gravatarId;
    String url;
    @SerializedName("html_url")
    String htmlUrl;
    @SerializedName("followers_url")
    String followersUrl;
    @SerializedName("following_url")
    String followingUrl;
    @SerializedName("gists_url")
    String gistsUrl;
    @SerializedName("starred_url")
    String starredUrl;
    @SerializedName("subscriptions_url")
    String subscriptionsUrl;
    @SerializedName("organizations_url")
    String organizationsUrl;
    @SerializedName("repos_url")
    String reposUrl;
    @SerializedName("events_url")
    String eventsUrl;
    @SerializedName("received_events_url")
    String receivedEventsUrl;
    String type;
    @SerializedName("site_admin")
    boolean siteAdmin;
    String name;
    String company;
    String blog;
    String location;
    String email;
    String hireable;
    String bio;
    @SerializedName("public_repos")
    int publicRepos;
    @SerializedName("public_gists")
    int publicGists;
    int followers;
    int following;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("updated_at")
    String updatedAt;

    public User(String login, int id, String avatarUrl, String gravatarId, String url,
                String htmlUrl, String followersUrl, String followingUrl, String gistsUrl,
                String starredUrl, String subscriptionsUrl, String organizationsUrl, String reposUrl,
                String eventsUrl, String receivedEventsUrl, String type, boolean siteAdmin,
                String name, String company, String blog, String location, String email,
                String hireable, String bio, int publicRepos, int publicGists, int followers,
                int following, String createdAt, String updatedAt) {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getType() {
        return type;
    }

    public boolean isSiteAdmin() {
        return siteAdmin;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getHireable() {
        return hireable;
    }

    public String getBio() {
        return bio;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
