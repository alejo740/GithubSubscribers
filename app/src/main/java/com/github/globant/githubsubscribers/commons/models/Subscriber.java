package com.github.globant.githubsubscribers.commons.models;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * Subscriber class that represents each Github subscriber
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 19/08/2016
 */
public class Subscriber {

    private final String login;
    private final String id;
    @SerializedName("avatar_url")
    private final String avataUrl;
    @SerializedName("gravatar_id")
    private final String gravatarId;
    private final String url;
    @SerializedName("html_url")
    private final String htmlUrl;
    @SerializedName("followers_url")
    private final String followersUrl;
    @SerializedName("following_url")
    private final String followingUrl;
    @SerializedName("gists_url")
    private final String gistsUrl;
    @SerializedName("starred_url")
    private final String starredUrl;
    @SerializedName("subscriptions_url")
    private final String subscriptionsUrl;
    @SerializedName("organizations_url")
    private final String organizationsUrl;
    @SerializedName("repos_url")
    private final String reposUrl;
    @SerializedName("events_url")
    private final String eventsUrl;
    @SerializedName("received_events_url")
    private final String receivedEventsUrl;
    private final String type;
    @SerializedName("site_admin")
    private final boolean siteAdmin;

    public Subscriber(String login, String id, String avataUrl, String gravatarId, String url,
                            String htmlUrl, String followersUrl, String followingUrl,
                            String gistsUrl, String starredUrl, String subscriptionsUrl,
                            String organizationsUrl, String reposUrl, String eventsUrl,
                            String receivedEventsUrl, String type, boolean siteAdmin) {
        this.login = login;
        this.id = id;
        this.avataUrl = avataUrl;
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
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getAvataUrl() {
        return avataUrl;
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");
        return result.toString();
    }

}
