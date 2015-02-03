package com.mojio.mojiosdk.models;

import java.util.ArrayList;

public class App {
	
	private int Type;
	private String Name;
	private String Description;
	private String Downloads;
	private ArrayList<String> RedirectUris;
	private int ApplicationType;
	private String _id;
	private boolean _delete;
	
	
	public App(){
		
	}


	public int getType() {
		return Type;
	}


	public void setType(int type) {
		Type = type;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getDownloads() {
		return Downloads;
	}


	public void setDownloads(String downloads) {
		Downloads = downloads;
	}


	public ArrayList<String> getRedirectUris() {
		return RedirectUris;
	}


	public void setRedirectUris(ArrayList<String> redirectUris) {
		RedirectUris = redirectUris;
	}


	public int getApplicationType() {
		return ApplicationType;
	}


	public void setApplicationType(int applicationType) {
		ApplicationType = applicationType;
	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public boolean is_delete() {
		return _delete;
	}


	public void set_delete(boolean _delete) {
		this._delete = _delete;
	}
	
}
