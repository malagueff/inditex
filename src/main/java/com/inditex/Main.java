package com.inditex;

import com.inditex.srv.GrillServiceImpl;

public class Main {

	public static void main(String[] args) {
		// Init service with files to read
		GrillServiceImpl service = new GrillServiceImpl("product.csv", "stock.csv", "size.csv");
		// Print products with stocks
		System.out.println(service.getStock());
	}
}
