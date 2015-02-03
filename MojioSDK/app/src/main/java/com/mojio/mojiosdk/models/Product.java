package com.mojio.mojiosdk.models;

public class Product {

	private int Type;
	private String AppId;
	private String Name;
	private String Description;
	private boolean Shipping;
	private boolean Taxable;
	private float Price;
	private boolean Discontinued;
	private String OwnerId;
	private String CreationDate;
	private String _id;
	private boolean _deleted;
	
	public Product(){
		
	}

	public int getType() {
		return Type;
	}

	public void setType(int type) {
		Type = type;
	}

	public String getAppId() {
		return AppId;
	}

	public void setAppId(String appId) {
		AppId = appId;
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

	public boolean isShipping() {
		return Shipping;
	}

	public void setShipping(boolean shipping) {
		Shipping = shipping;
	}

	public boolean isTaxable() {
		return Taxable;
	}

	public void setTaxable(boolean taxable) {
		Taxable = taxable;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
	}

	public boolean isDiscontinued() {
		return Discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		Discontinued = discontinued;
	}

	public String getOwnerId() {
		return OwnerId;
	}

	public void setOwnerId(String ownerId) {
		OwnerId = ownerId;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public boolean is_deleted() {
		return _deleted;
	}

	public void set_deleted(boolean _deleted) {
		this._deleted = _deleted;
	}
}
