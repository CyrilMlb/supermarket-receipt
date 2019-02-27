package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;
import java.util.*;

public class Bundle{
	private HashMap<Product, Double> hmProducts;
	
	public Bundle(){
		this.hmProducts = new HashMap<Product, Double>();
	}
	
	public HashMap<Product, Double> getProducts(){
		return this.hmProducts;
	}

	public void addProduct(Product product, double quantity){
		this.hmProducts.put(product, new Double(quantity) );
	}

	public boolean isEmpty(){
		return this.hmProducts.isEmpty();
	}
}
