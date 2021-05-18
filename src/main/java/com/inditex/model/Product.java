package com.inditex.model;

import java.util.List;

public class Product implements Comparable<Product> {
	
	String id;
	Integer sequence;
	List<Size> sizes;

	public Product(String id, Integer sequence, List<Size> sizes) {
		super();
		this.id = id;
		this.sequence = sequence;
		this.sizes = sizes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public List<Size> getSizes() {
		return sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", sequence=" + sequence + ", sizes=" + sizes + "]";
	}

	@Override
	public int compareTo(Product p) {
		return this.getSequence().compareTo(p.getSequence());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
