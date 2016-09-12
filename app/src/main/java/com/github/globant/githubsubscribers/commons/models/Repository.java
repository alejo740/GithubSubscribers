package com.github.globant.githubsubscribers.commons.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Repository class that represents each Github subscriber reposiroty information
 *
 * @author juan.herrera
 * @since 31/08/2016
 */
public class Repository implements Parcelable {

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

    protected Repository(Parcel in) {
        id = in.readString();
        name = in.readString();
        fullName = in.readString();
        htmlUrl = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(htmlUrl);
    }
}
