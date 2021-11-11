package io.javaminds.application.global;

import java.util.ArrayList;
import java.util.List;

import io.javaminds.application.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	static {
		cart = new ArrayList<Product>();
	}

}
