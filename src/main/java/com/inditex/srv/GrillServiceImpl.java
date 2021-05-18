package com.inditex.srv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.inditex.model.Product;
import com.inditex.model.Size;

public class GrillServiceImpl {

	// Create the class variables
	private Map<String, Product> productos = new HashMap<>();
	private Map<String, Integer> stocks = new HashMap<>();

	public GrillServiceImpl(String fileProduct, String fileStock, String fileSize) {
		try (BufferedReader brProduct = new BufferedReader(new FileReader(fileProduct));
				BufferedReader brStock = new BufferedReader(new FileReader(fileStock));
				BufferedReader brSize = new BufferedReader(new FileReader(fileSize))) {
			// Read products
			String line = brProduct.readLine();
			while (line != null) {
				List<String> parts = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
				// Insert products without the sizes into map
				this.productos.put(parts.get(0),
						new Product(parts.get(0), Integer.valueOf(parts.get(1)), new ArrayList<>()));
				line = brProduct.readLine();
			}
			
			// Read stock
			line = brStock.readLine();
			while (line != null) {
				List<String> parts = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
				// Insert stock into map
				this.stocks.put(parts.get(0), Integer.valueOf(parts.get(1)));
				line = brStock.readLine();
			}
			
			// Read sizes
			line = brSize.readLine();
			while (line != null) {
				List<String> parts = Arrays.stream(line.split(",")).map(String::trim).collect(Collectors.toList());
				String productId = parts.get(1);
				String sizeId = parts.get(0);
				// Get product from map
				Product pro = this.productos.get(productId);
				if (null != pro) {
					// Add size and stock into product map
					pro.getSizes().add(new Size(parts.get(0), Boolean.valueOf(parts.get(2)),
							Boolean.valueOf(parts.get(3)), this.stocks.get(sizeId)));
				}
				line = brSize.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error load files: " + e.getMessage());
		}
		// For garbage collector to remove it because no
		this.stocks = null;
	}

	// Get the stock of the products
	public String getStock() {
		return this.productos.entrySet().stream().filter(f -> hasStock(f.getValue())).sorted(Entry.comparingByValue())
				.map(Entry::getKey).collect(Collectors.joining(","));
	}

	// Get if product has stock
	public boolean hasStock(Product item) {
		boolean result = false;
		List<Size> sizes = item.getSizes();
		// Check has any special sizes
		if (sizes.stream().anyMatch(Size::isSpecial)) {
			result = (sizes.stream().filter(Size::isSpecial).anyMatch(GrillServiceImpl::hasStockSize))
					&& (sizes.stream().filter(Size::isNotSpecial).anyMatch(GrillServiceImpl::hasStockSize));
		} else if (sizes.stream().anyMatch(GrillServiceImpl::hasStockSize)) {
			result = true;
		}
		return result;
	}

	// Get if size has stock
	public static boolean hasStockSize(Size size) {
		return (size.getQuantity() != null && size.getQuantity() > 0) || size.isBackSoon();
	}
}
