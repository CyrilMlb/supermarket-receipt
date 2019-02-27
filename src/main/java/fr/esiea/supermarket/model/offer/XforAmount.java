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
	
	public double getAmount(){
		return this.amount;
	}
	
	public int getX(){
		return this.x;
	}
	
	@Override
	public Discount getSpecialDiscount(double quantity, double unitPrice){
		int quantityAsInt = (int) quantity;
		Discount discount = null;
		if (quantityAsInt >= this.x) {
            double discountAmount = this.amount * (int)(quantityAsInt / this.x); 
            /*
	            Erreur dans le code, avec DiscountN on retourne le prix total des produits avec la réduction
	            		+ quantityAsInt % 5 * unitPrice;            
	            double discountN = unitPrice * quantity - total;
            */
            discount = new Discount(this.product, this.x + " for " + amount + "$", discountAmount);
        }
		return discount;
	}
}