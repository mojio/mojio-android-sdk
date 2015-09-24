package io.moj.mobile.android.sdk.models.observers;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for a script observer.
 * Created by jian on 01/04/2015.
 */
public class ScriptObserver extends ConditionalObserverBase {

    @SerializedName("Script")
    private String script;

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "ScriptObserver{" +
                "script='" + script + '\'' +
                '}';
    }
}
