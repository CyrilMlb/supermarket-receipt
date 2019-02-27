package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;

public class ThreeForTwo implements Offer{
	private Product product;
	
	public ThreeForTwo(Product product){
		this.product = product;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	public Discount getSpecialDiscount(double quantity, double unitPrice){
		int quantityAsInt = (int) quantity;
		Discount discount = null;
		if (quantityAsInt >= 3) {
            double discountAmount = quantity * unitPrice - ((quantity / 3 * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
            discount = new Discount(this.product, "3 for 2", discountAmount);
        }
		return discount;
	}
}