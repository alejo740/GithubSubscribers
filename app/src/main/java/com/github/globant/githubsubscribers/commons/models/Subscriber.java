package com.github.globant.githubsubscribers.commons.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * Subscriber class that represents each Github subscriber
 *
 * @author edwin.cobos
 * @author juan.herrera
 * @since 19/08/2016
 */
public class Subscriber implements Parcelable{

    private final String login;
    private final String id;
    @SerializedName("avatar_url")
    private final String avataUrl;

    public Subscriber(String login, String id, String avataUrl) {
        this.login = login;
        this.id = id;
        this.avataUrl = avataUrl;
    }

    protected Subscriber(Parcel in) {
        login = in.readString();
        id = in.readString();
        avataUrl = in.readString();
    }

    public static final Creator<Subscriber> CREATOR = new Creator<Subscriber>() {
        @Override
        public Subscriber createFromParcel(Parcel in) {
            return new Subscriber(in);
        }

        @Override
        public Subscriber[] newArray(int size) {
            return new Subscriber[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getAvataUrl() {
        return avataUrl;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(id);
        dest.writeString(avataUrl);
    }
}
