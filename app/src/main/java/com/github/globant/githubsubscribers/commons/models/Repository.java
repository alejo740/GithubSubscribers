package com.github.globant.githubsubscribers.commons.models;

import com.google.gson.annotations.SerializedName;

/**
 * Repository class that represents each Github subscriber reposiroty information
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class Repository {

    private String id;
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("html_url")
    private String htmlUrl;

    public Repository(String id, String name, String fullName, String htmlUrl) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.htmlUrl = htmlUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

}
