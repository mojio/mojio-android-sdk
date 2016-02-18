package io.moj.mobile.android.sdk.push;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for a request to create an observer.
 * Created by skidson on 16-02-18.
 */
public class ObserverCreationRequest {

    private String Key;
    private String CreatedOn;
    private String LastModified;
    private String ExpiryDate;
    private String Name;
    private String Subject;
    private List<String> Fields;
    private Condition PropertyChanged;
    private Condition Threshold;
    private Condition Debounce;
    private Condition Throttle;
    private Transport Transport;
    private Observer.Type Type;

    public static class Builder {
        private ObserverCreationRequest request;

        /**
         * Constructs an Observer builder.
         * @param key uniquely identifies your observer; it must be unique for the user,
         *            application and entity. It cannot be edited.
         */
        public Builder(String key) {
            request = new ObserverCreationRequest();
            request.Key = key;
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
            request.Subject = subject;
            return this;
        }

        /**
         * The Type of entity that is being observed. Either {@link Observer#Type#MOJIO},
         * {@link Observer#Type#VEHICLE}, or {@link Observer#Type#USER}. This is automatically set on creation and
         * cannot be edited.
         * @param type
         * @return
         */
        public Builder type(Observer.Type type) {
            request.Type = type;
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
            request.Transport = transport;
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
            switch (condition.getType()) {
                case PROPERTY_CHANGED:
                    request.PropertyChanged = condition;
                    break;
                case THRESHOLD:
                    request.Threshold = condition;
                    break;
                case DEBOUNCE:
                    request.Debounce = condition;
                    break;
                case THROTTLE:
                    request.Throttle = condition;
                    break;
            }
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
            if (request.Fields == null)
                request.Fields = new ArrayList<>();
            request.Fields.add(field);
            return this;
        }

        /**
         * Constructs the {@link Observer}.
         * @return
         */
        public ObserverCreationRequest build() {
            return request;
        }
    }

}
