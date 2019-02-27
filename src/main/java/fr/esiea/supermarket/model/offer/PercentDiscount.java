package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;

public class PercentDiscount implements Offer{
	private Product product;
	private double percentAmount;
	
	public PercentDiscount(Product product, double percentAmount){
		this.product = product;
		this.percentAmount = percentAmount;
	}
	
	@Override
	public Product getProduct(){
		return this.product;
	}
	
	public double getPercentAmount(){
		return this.percentAmount;
	}
	
	@Override
	public Discount getSpecialDiscount(double quantity, double unitPrice){
		int quantityAsInt = (int) quantity;
		Discount discount = null;
		if (quantityAsInt >= 1) {
            double discountAmount = quantity * unitPrice * this.percentAmount / 100.0;
            discount = new Discount(this.product, this.percentAmount + "% off", discountAmount);
        }
		return discount;
	}
}