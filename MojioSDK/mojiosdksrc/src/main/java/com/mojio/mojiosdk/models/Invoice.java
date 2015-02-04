package com.mojio.mojiosdk.models;

import java.util.ArrayList;

public class Invoice {

	
	private int Type;
	private String BuyerId;
	private String AppId;
	private String Date;
	private String DueDate;
	private boolean DueOnShip;
	private ArrayList<String> Items;
	private String PromoCode;
	private String Currency;
	private float SubTotal;
	private float Tax;
	private float Shipping;
	private float Total;
	private Address Address;
	private float Owing;
	private String OwingReason;
	private boolean Shipped;
	private boolean Paid;
	private String StripeId;
	private String _id;
	private boolean _deleted;
	
	
	public Invoice(){
		
	}


	public int getType() {
		return Type;
	}


	public void setType(int type) {
		Type = type;
	}


	public String getBuyerId() {
		return BuyerId;
	}


	public void setBuyerId(String buyerId) {
		BuyerId = buyerId;
	}


	public String getAppId() {
		return AppId;
	}


	public void setAppId(String appId) {
		AppId = appId;
	}


	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		Date = date;
	}


	public String getDueDate() {
		return DueDate;
	}


	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}


	public boolean isDueOnShip() {
		return DueOnShip;
	}


	public void setDueOnShip(boolean dueOnShip) {
		DueOnShip = dueOnShip;
	}


	public ArrayList<String> getItems() {
		return Items;
	}


	public void setItems(ArrayList<String> items) {
		Items = items;
	}


	public String getPromoCode() {
		return PromoCode;
	}


	public void setPromoCode(String promoCode) {
		PromoCode = promoCode;
	}


	public String getCurrency() {
		return Currency;
	}


	public void setCurrency(String currency) {
		Currency = currency;
	}


	public float getSubTotal() {
		return SubTotal;
	}


	public void setSubTotal(float subTotal) {
		SubTotal = subTotal;
	}


	public float getTax() {
		return Tax;
	}


	public void setTax(float tax) {
		Tax = tax;
	}


	public float getShipping() {
		return Shipping;
	}


	public void setShipping(float shipping) {
		Shipping = shipping;
	}


	public float getTotal() {
		return Total;
	}


	public void setTotal(float total) {
		Total = total;
	}


	public Address getAddress() {
		return Address;
	}


	public void setAddress(Address address) {
		Address = address;
	}


	public float getOwing() {
		return Owing;
	}


	public void setOwing(float owing) {
		Owing = owing;
	}


	public String getOwingReason() {
		return OwingReason;
	}


	public void setOwingReason(String owingReason) {
		OwingReason = owingReason;
	}


	public boolean isShipped() {
		return Shipped;
	}


	public void setShipped(boolean shipped) {
		Shipped = shipped;
	}


	public boolean isPaid() {
		return Paid;
	}


	public void setPaid(boolean paid) {
		Paid = paid;
	}


	public String getStripeId() {
		return StripeId;
	}


	public void setStripeId(String stripeId) {
		StripeId = stripeId;
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
