package fr.esiea.supermarket.model;

import java.util.*;
import fr.esiea.supermarket.model.offer.*;

public class Teller {

    private final SupermarketCatalog catalog;
    private Map<Product, Offer> offers = new HashMap<>();
    private ArrayList<Bundle> bundles = new ArrayList<Bundle>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(Offer offer) {
        if(offer != null){
            this.offers.put(offer.getProduct(), offer);
        }
    }

    public void addBundle(Bundle bundle){
        if(!bundle.isEmpty()){
            this.bundles.add(bundle);
        }
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        theCart.handleBundles(this, this.bundles, this.catalog);

        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);
            double price = quantity * unitPrice;
            receipt.addProduct(p, quantity, unitPrice, price);
        }
        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }
}
