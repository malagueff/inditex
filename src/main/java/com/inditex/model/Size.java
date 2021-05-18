package com.inditex.model;

public class Size {

	String id;
	boolean backSoon;
	boolean special;
	Integer quantity;

	public Size(String id, boolean backSoon, boolean special, Integer quantity) {
		super();
		this.id = id;
		this.backSoon = backSoon;
		this.special = special;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isBackSoon() {
		return backSoon;
	}

	public void setBackSoon(boolean backSoon) {
		this.backSoon = backSoon;
	}

	public boolean isSpecial() {
		return special;
	}
	
	public boolean isNotSpecial() {
		return !special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", backSoon=" + backSoon + ", special=" + special + ", quantity=" + quantity + "]";
	}
}
