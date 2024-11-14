package com.ecom.app.util;

import java.util.ArrayList;
import java.util.List;

import com.ecom.app.entity.Product;

public class UtilClass {

	public static List<Product> cart;
	
	public static Integer quantity;
	
	static {
		cart=new ArrayList<>();
		quantity=1;
	}
	
	
}
