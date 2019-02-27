package fr.esiea.supermarket.model;

import java.util.*;
import fr.esiea.supermarket.model.offer.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();


    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
<<<<<<< HEAD
                double unitPrice = catalog.getUnitPrice(p);

                Discount discount = null;
                discount = offer.getSpecialDiscount(quantity, unitPrice);
=======
                int quantityAsInt = (int) quantity;
                double unitPrice = catalog.getUnitPrice(p);

                Discount discount = null;
                discount = offer.getSpecialDiscount(quantityAsInt, unitPrice);
>>>>>>> 3c58d3eeb3e4f1323cace2d4b1349706397ec7c6
                if (discount != null)
                    receipt.addDiscount(discount);
            }
        }
    }
}
