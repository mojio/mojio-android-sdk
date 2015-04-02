package com.mojio.mojiosdk.models.Observers;

import java.util.ArrayList;

/**
 * Created by jian on 01/04/2015.
 */
public class Observer {

    public String Type;
    public String Name;
    public String ObserverType;
    public String AppId;
    public String OwnerId;
    public String Parent;
    public String ParentId;
    public String Subject;
    public String SubjectId;
    public int Transports;
    public String Status;
    public ArrayList<ObserverToken> Tokens;
    public String TimeWindow;
    public boolean BroadcastOnlyRecent;
    public String Throttle;
    public String NextAllowedBroadcast;
    public String _id;
    public boolean _deleted;
}
