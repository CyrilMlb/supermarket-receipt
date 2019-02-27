package fr.esiea.supermarket.model.offer;

import fr.esiea.supermarket.model.*;

public interface Offer {
    Product getProduct();
	Discount getSpecialDiscount(double quantity, double unitPrice);
}