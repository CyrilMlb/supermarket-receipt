package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;

public class XforAmount implements Offer{
	private Product product;
	private double amount;
	private int x;
	
	public XforAmount(int x, Product product, double amount){
		this.x = x;
		this.product = product;
		this.amount = amount;
	}
	
	@Override
	public Product getProduct(){
		return this.product;
	}

	@Override
	public Discount getSpecialDiscount(double quantity, double unitPrice){
		int quantityAsInt = (int) quantity;
		Discount discount = null;
		if (quantityAsInt >= this.x) {
            double total = this.amount * quantityAsInt / this.x + quantityAsInt % this.x * unitPrice;            
	        double discountN = unitPrice * quantity - total;
            discount = new Discount(this.product, this.x + " for " + amount + "$", discountN);
        }
        else{
			new Discount(this.product, "No Discount", 0.0);
        }

		return discount;
	}
}