package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;
import java.util.*;

public class Bundle{
	private HashMap<Product, Double> hmProducts;
	
	public Bundle(HashMap<Product, Double> hmProducts){
		this.hmProducts = new HashMap<Product, Double>(hmProducts);
	}
	
	public HashMap<Product, Double> getProducts(){
		return this.hmProducts;
	}

	public boolean isEmpty(){
		return this.hmProducts.isEmpty();
	}
}