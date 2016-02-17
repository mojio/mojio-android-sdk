package io.moj.mobile.android.sdk.push;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model object for an observer.
 * Created by skidson on 16-02-16.
 */
public class Observer {

    private String Key;
    private String CreatedOn;
    private String LastModified;
    private String ExpiryDate;
    private String Name;
    private String Subject;
    private Type Type;
    private List<String> Fields;
    private List<Transport> Transports;
    private Map<Condition.Type, Condition> Conditions;

    public Observer() {}

    public Observer(String key) {
        this.Key = key;
    }

    public String getCreatedOn() {
        return CreatedOn;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public List<String> getFields() {
        return Fields;
    }

    public String getKey() {
        return Key;
    }

    public String getLastModified() {
        return LastModified;
    }

    public String getName() {
        return Name;
    }

    public String getSubject() {
        return Subject;
    }


    public void setCreatedOn(String createdOn) {
        CreatedOn = createdOn;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public void setKey(String key) {
        Key = key;
    }

    public void setLastModified(String lastModified) {
        LastModified = lastModified;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public void setFields(List<String> fields) {
        Fields = fields;
    }

    public List<Transport> getTransports() {
        return Transports;
    }

    public void setTransports(List<Transport> transports) {
        Transports = transports;
    }

    public Map<Condition.Type, Condition> getConditions() {
        return Conditions;
    }

    public void setConditions(Map<Condition.Type, Condition> conditions) {
        this.Conditions = conditions;
    }

    public Observer.Type getType() {
        return Type;
    }

    public void setType(Observer.Type type) {
        Type = type;
    }

    public static class Builder {
        private Observer observer;
        private List<String> fields;
        private List<Transport> transports;
        private Map<Condition.Type, Condition> conditions;

        /**
         * Constructs an Observer builder.
         * @param key uniquely identifies your observer; it must be unique for the user,
         *            application and entity. It cannot be edited.
         */
        public Builder(String key) {
            observer = new Observer(key);
        }

        /**
         * The Id of the entity that is being observed. If an entity Id is not passed in when
         * creating an observer it will broadcast changes for all entities of that type that the
         * user has read permissions for. If an Id is passed in it becomes the Subject on the
         * observer. This is automatically set on creation and cannot be edited.
         * @param subject
         * @return
         */
        public Builder subject(String subject) {
            observer.setSubject(subject);
            return this;
        }

        /**
         * The Type of entity that is being observed. Either {@link Type#MOJIO},
         * {@link Type#VEHICLE}, or {@link Type#USER}. This is automatically set on creation and
         * cannot be edited.
         * @param type
         * @return
         */
        public Builder type(Type type) {
            observer.setType(type);
            return this;
        }

        /**
         * How the observer should contact your application. Each observer should only have one
         * transport. If defaults have been set for a specific transport type they will
         * automatically copied over to the transport.
         * @param transport
         * @return
         */
        public Builder transport(Transport transport) {
            if (transports == null)
                transports = new ArrayList<>();
            transports.add(transport);
            return this;
        }

        /**
         * Limits the behavior of an Observer. An Observer can have up to 4 Conditions, one of each
         * type. If none of these are set the Observer will broadcast a message anytime the entity
         * changes in any way.
         * @param condition
         * @return
         */
        public Builder condition(Condition condition) {
            if (conditions == null)
                conditions = new HashMap<>();
            conditions.put(condition.getType(), condition);
            return this;
        }

        /**
         * Specifies what fields of the entity will be broadcast when a change occurs. When creating
         * or updating an Observer passing in an empty array or null will default to all fields the
         * user has permission for.
         * @param field
         * @return
         */
        public Builder field(String field) {
            if (fields == null)
                fields = new ArrayList<>();
            fields.add(field);
            return this;
        }

        /**
         * Constructs the {@link Observer}.
         * @return
         */
        public Observer build() {
            observer.setTransports(transports);
            observer.setConditions(conditions);
            observer.setFields(fields);
            return observer;
        }
    }

    public enum Type {
        @SerializedName("mojio")
        MOJIO("mojio"),

        @SerializedName("vehicle")
        VEHICLE("vehicle"),

        @SerializedName("user")
        USER("user");

        private final String key;

        Type(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public static Type fromKey(String key) {
            for (Type type : values()) {
                if (type.getKey().equals(key))
                    return type;
            }
            return null;
        }
    }
}
