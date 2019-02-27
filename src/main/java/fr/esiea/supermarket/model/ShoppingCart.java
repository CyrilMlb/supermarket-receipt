package fr.esiea.supermarket.model;

import java.util.*;
import java.util.logging.*;
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
                int quantityAsInt = (int) quantity;
                double unitPrice = catalog.getUnitPrice(p);
                Discount discount = null;
                discount = offer.getSpecialDiscount(quantityAsInt, unitPrice);
                if(discount!=null){
                    receipt.addDiscount(discount);
                }
            }
        }
    }

    void handleBundles(Teller teller, ArrayList<Bundle> bundles, SupermarketCatalog catalog) {
        for (Bundle b : bundles) {
            int nbBundle = 1;
            boolean containsBundle = true;
            int nbBundles = 1000;
            for( Product p : b.getProducts().keySet() ){
                if (productQuantities.containsKey(p) && productQuantities.get(p) >= b.getProducts().get(p)){
                    if( nbBundles > productQuantities.get(p) / b.getProducts().get(p) ){
                        nbBundles = (int)(productQuantities.get(p) / b.getProducts().get(p));
                    }
                }
                else{
                    containsBundle = false;
                }
            }
            if(containsBundle){
                double totalAmount = 0.0;
                for( Product p : b.getProducts().keySet() ){
                    totalAmount += catalog.getUnitPrice(p) * nbBundles;
                    productQuantities.put(p, new Double(productQuantities.get(p) - b.getProducts().get(p) * nbBundles ) );
                }

                Product product = new Product("Bundle " + nbBundle, ProductUnit.Each);
                catalog.addProduct(product, totalAmount);
                productQuantities.put(product, new Double(1.0) );

                teller.addSpecialOffer(new PercentDiscount(product, 10));
            }
        }
    }
}
